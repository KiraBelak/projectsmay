package com.alldata.CliProManagementTool.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Users")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @NotNull
    @Column(name="name")
    private String name;


    @NotNull
    @Column(name="alias")
    private String alias;

    @NotNull
    @Column(name="role")
    private String role;

    @NotNull
    @Email
    @Column(name="email")
    private String email;

    @NotNull
    @Size(min=6,max=30)
    @Column(name="password")
    private String password;

}
