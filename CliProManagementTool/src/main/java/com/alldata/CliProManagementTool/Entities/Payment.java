package com.alldata.CliProManagementTool.Entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @DecimalMin("0.0")
    @Column(name="quantity")
    private double quantity;

    @NotNull
    @Column(name="payment_description")
    private String paymentDescriptions;

    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getPaymentDescriptions() {
        return paymentDescriptions;
    }

    public void setPaymentDescriptions(String paymentDescriptions) {
        this.paymentDescriptions = paymentDescriptions;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }
}
