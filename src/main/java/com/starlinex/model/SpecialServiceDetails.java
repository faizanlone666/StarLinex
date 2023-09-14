package com.starlinex.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpecialServiceDetails {
    private String awbNbr;
    private String specialServiceName;
    private String specialServicePcs;
}
