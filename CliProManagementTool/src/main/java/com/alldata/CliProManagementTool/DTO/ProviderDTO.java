package com.alldata.CliProManagementTool.DTO;/*
 * @created 18/05/2025
 * @project CliProManagementTool
 * @author Noktuos
 */

import com.alldata.CliProManagementTool.Entities.Payment;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class ProviderDTO {

    private Long id;
    @NotBlank(message = "Company name can't be null or empty")
    private String companyName;
    private List<Payment> payments;
    @NotBlank(message = "Product name can't be null or empty")
    private String providerProductName;
    @NotBlank(message = "Description can't be null or empty")
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
}
