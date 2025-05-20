package com.alldata.CliProManagementTool.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name="payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double quantity;

    @NotNull
    @Column(name="payment_description")
    private String paymentDescriptions;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name="provider_id")
    private Long provider;

    public Payment(String paymentDescriptions, double quantity) {
        this.paymentDescriptions = paymentDescriptions;
        this.quantity = quantity;
    }

    public Payment() {
    }
}
