package com.bulbul.ribana.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Role {

    @Id
    @SequenceGenerator(name = "ROLE_SEQ_GENERATOR", sequenceName = "ROLE_SEQ", initialValue = 101)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROLE_SEQ_GENERATOR")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USERR_ID", referencedColumnName = "ID")
    private Userr userr;

    @Size(max = 64)
    @NotBlank
    private String role;

}
