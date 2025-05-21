package com.alldata.CliProManagementTool.DTO;/*
 * @created 18/05/2025
 * @project CliProManagementTool
 * @author Noktuos
 */

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class PaymentDTO {

    private Long id;
    @DecimalMin("0.0")
    private double quantity;

    private Long clientId;
    private Long providerId;
    @NotBlank(message="A brief description must be set to every payment. Can't be blank or null")
    private String paymentDescriptions;

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

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getProviderId() {
        return providerId;
    }

    public void setProviderId(Long providerId) {
        this.providerId = providerId;
    }

    public String getPaymentDescriptions() {
        return paymentDescriptions;
    }

    public void setPaymentDescriptions(String paymentDescriptions) {
        this.paymentDescriptions = paymentDescriptions;
    }
}
