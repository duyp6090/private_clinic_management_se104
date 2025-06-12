package com.example.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LaboratoryDTO {
    private String labName;
    private Double result;
    private String unit;
}
