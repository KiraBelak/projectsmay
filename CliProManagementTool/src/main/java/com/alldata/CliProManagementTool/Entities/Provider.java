package com.alldata.CliProManagementTool.Entities;



import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Entity
@Table(name="provider")
@Getter
@Setter
@Validated
public class Provider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="companyName")
    @NotNull
    private String companyName;

    @Column(name="payments")
    @JsonIgnore
    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Payment> payments;

    @Column(name="provider_product_name")
    @NotBlank(message="Product name is mandatory")
    private String providerProductName;

    @Column(name="description")
    @NotNull
    private String description;


    public Provider(String companyName, String providerProductName, String description) {
        this.companyName = companyName;
        this.providerProductName = providerProductName;
        this.description = description;
    }


    public void addPayment(Payment payment){
        payments.add(payment);
    }

    public Provider() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public String getProviderProductName() {
        return providerProductName;
    }

    public void setProviderProductName(String providerProductName) {
        this.providerProductName = providerProductName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPayments(Optional<Payment> byId) {
    }
}
