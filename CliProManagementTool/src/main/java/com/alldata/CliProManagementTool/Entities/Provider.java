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
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Provider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="provider_id")
    private Long prviderId;

    @Column(name="company_name")
    @NotNull
    private String companyName;

    @Column(name="payments")
    @NotNull
    @JsonIgnore
    @OneToMany(mappedBy = "id")
    private List<Payment> payments;

    @Column(name="provider_product_name")
    @NotNull
    private String providerProductName;

    @Column(name="description")
    @NotNull
    private String description;

}
