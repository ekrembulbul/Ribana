package com.bulbul.ribana.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Userr {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERR_SEQUENCE_GENERATOR")
    @SequenceGenerator(name = "USERR_SEQUENCE_GENERATOR", sequenceName = "USERR_SEQUENCE", initialValue = 101)
    private Long id;

    @Size(max = 64)
    @NotBlank
    private String username;

    @Size(max = 60)
    @NotBlank
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
