package com.bulbul.ribana.config;

import com.bulbul.ribana.config.properties.settings.RequestMatchersConfig;
import com.bulbul.ribana.data.enumm.EnumRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;
import java.util.EnumMap;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    RequestMatchersConfig requestMatchersProperties;

    @Autowired
    public void configureSecurity(AuthenticationManagerBuilder auth, DataSource dataSource) throws Exception {
        String usersByUsernameQuery = """
                SELECT
                    USERNAME,
                    PASSWORD,
                    ACTIVE
                FROM USERR
                WHERE USERNAME = ?
                """;

        String authoritiesByUsernameQuery = """
                SELECT
                    U.USERNAME,
                    R.ROLE
                FROM USERR U, ROLE R
                WHERE
                    U.ID = R.USERR_ID AND
                    U.USERNAME = ?
                """;

        auth.jdbcAuthentication()
            .dataSource(dataSource)
            .passwordEncoder(new BCryptPasswordEncoder())
            .usersByUsernameQuery(usersByUsernameQuery)
            .authoritiesByUsernameQuery(authoritiesByUsernameQuery);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        EnumMap<EnumRole, String[]> roleRequestMatchers = new EnumMap<>(EnumRole.class);
        roleRequestMatchers.put(EnumRole.DEV, requestMatchersProperties.roleDev.toArray(String[]::new));
        roleRequestMatchers.put(EnumRole.ADMIN, requestMatchersProperties.roleAdmin.toArray(String[]::new));

        http.authorizeHttpRequests(registry -> registry
                .requestMatchers(roleRequestMatchers.get(EnumRole.DEV)).hasRole(EnumRole.DEV.name())
                .requestMatchers(roleRequestMatchers.get(EnumRole.ADMIN)).hasRole(EnumRole.ADMIN.name())
                .requestMatchers(HttpMethod.POST).hasRole(EnumRole.ADMIN.name())
                .requestMatchers(HttpMethod.PUT).hasRole(EnumRole.ADMIN.name())
                .requestMatchers(HttpMethod.DELETE).hasRole(EnumRole.ADMIN.name())
                .anyRequest().permitAll());

        http.httpBasic()
            .and()
            .csrf().disable();

        return http.build();
    }

}
