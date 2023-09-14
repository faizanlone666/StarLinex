package com.starlinex.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeightAndDimensionDetails {
    private String awbNbr;
    private String boxNo;
    private String actualWt;
    private String lcm;
    private String bcm;
    private String hcm;
    private String volumetricWt;
}
