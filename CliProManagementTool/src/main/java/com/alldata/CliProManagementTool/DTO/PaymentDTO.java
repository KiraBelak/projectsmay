package com.alldata.CliProManagementTool.DTO;/*
 * @created 18/05/2025
 * @project CliProManagementTool
 * @author Noktuos
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {

    private Long id;
    private double quantity;
    private Long clientId;
    private Long providerId;
    private String paymentDescriptions;
}
