package com.alldata.CliProManagementTool.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

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

    @ManyToOne
    @JoinColumn(name="provider_id")
    private Provider provider;

    public Payment(double quantity, String paymentDescriptions, Client client, Provider provider) {
        this.quantity = quantity;
        this.paymentDescriptions = paymentDescriptions;
        this.client = client;
        this.provider = provider;
    }

    public Payment() {
    }
}
