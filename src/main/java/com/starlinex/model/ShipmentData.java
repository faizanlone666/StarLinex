package com.starlinex.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShipmentData {
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
