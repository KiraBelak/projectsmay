package com.alldata.CliProManagementTool.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="client")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="client_id")
    private Long clientId;

    @Column(name="name")
    private String name;

    @Column(name="address")
    private String address;

    @Column(name="email")
    private String email;

    @Column(name="representative")
    private String representative;

    @Column(name="payments")
    private Payment payment;

    @OneToMany(mappedBy = "id")
    private List<Payment> payments;

}
