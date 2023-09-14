package com.starlinex.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class WeightAndDimensions {

    @Id
    @GeneratedValue
    private Integer id;
    private String awbNbr;
    private String boxNo;
    private String actualWt;
    private String lcm;
    private String bcm;
    private String hcm;
    private String volumetricWt;
}
