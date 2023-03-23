package com.bulbul.ribana.entity;

import com.bulbul.ribana.CustomUserr;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@SqlResultSetMapping(
        name = "CustomUserrMapping",
        classes = @ConstructorResult(
                targetClass = CustomUserr.class,
                columns = {
                        @ColumnResult(name = "name", type = String.class)}))
@Data
@Entity
public class Userr {

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        private Long id;

        @Size(max = 64)
        @NotBlank
        private String userName;

        @Size(max = 64)
        private String name;

        @Size(max = 64)
        private String surname;

}
