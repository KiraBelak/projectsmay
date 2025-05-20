package com.alldata.mobsell.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhoneDTO {
    private String make;
    private String model;
    private String cpu;
    private Integer ram;
    private Double price;
}
