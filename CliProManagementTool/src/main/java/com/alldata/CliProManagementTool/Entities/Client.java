package com.alldata.CliProManagementTool.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    private String name;
    private String address;
    private String email;
    private String representative;

    @OneToMany(mappedBy = "id", orphanRemoval = true , cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Payment> payments;

    public Client(String name, String address, String email, String representative) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.representative = representative;
    }

    public void addPayment(Payment payment){
        payments.add(payment);
    }

    public Client() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRepresentative() {
        return representative;
    }

    public void setRepresentative(String representative) {
        this.representative = representative;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }
}

