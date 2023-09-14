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
public class ShipmentDetails {

    @Id
    @GeneratedValue
    private Integer id;
    private String awbNbr;
    private String shipmentBoxNo;
    private String shipmentDescription;
    private String shipmentHsCode;
    private String shipmentUnityType;
    private String shipmentQuantity;
    private String shipmentUnitWeight;
    private String shipmentIgst;
    private String shipmentUnitRates;
    private String shipmentAmount;
}
