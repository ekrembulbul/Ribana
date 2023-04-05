package com.bulbul.ribana.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROLE_SEQUENCE_GENERATOR")
    @SequenceGenerator(name = "ROLE_SEQUENCE_GENERATOR", sequenceName = "ROLE_SEQUENCE", initialValue = 101)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USERR_ID", referencedColumnName = "ID")
    private Userr userr;

    @Size(max = 64)
    @NotBlank
    private String role;

}
