package com.alldata.CliProManagementTool.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name="payment")
@NoArgsConstructor
@AllArgsConstructor
public class Payment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name="quantity")
    private double quantity;

    @NotNull
    @Column(name="client_id")
    @JoinColumn(name="client_id")
    private Long clientId;

    @NotNull
    @Column(name="provider_id")
    @JoinColumn(name="provider_id")
    private Long providerId;

    @NotNull
    @Column(name="payment_description")
    @Size(min=4,max=255)
    private String paymentDescriptions;



}
