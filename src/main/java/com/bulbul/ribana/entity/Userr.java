package com.bulbul.ribana.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@SuppressWarnings("unused")
@Data
@Entity
public class Userr {

    @Id
    @SequenceGenerator(name = "USERR_SEQ_GENERATOR", sequenceName = "USERR_SEQ", initialValue = 101)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERR_SEQ_GENERATOR")
    private Long id;

    @Size(max = 64)
    @NotBlank
    @Column(unique = true)
    private String username;

    @Size(max = 60)
    @NotBlank
    @Column(unique = true)
    private String password;

    @Size(max = 64)
    @NotBlank
    private String name;

    @Size(max = 64)
    @NotBlank
    private String surname;

    @NotNull
    private Boolean active;

}
