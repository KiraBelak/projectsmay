package com.alldata.CliProManagementTool.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="provider")
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
    @NotNull
    private String providerProductName;

    @Column(name="description")
    @NotNull
    private String description;

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

    public Provider( String companyName, String providerProductName, String description) {
        this.companyName = companyName;
        this.providerProductName = providerProductName;
        this.description = description;
    }

    public Provider() {
    }
}
