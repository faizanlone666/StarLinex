package com.starlinex.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AirWayBill {

    @Id
    @GeneratedValue
    private Integer id;
    private Integer userId;
    private String awbNbr;
    private String destination;
    private String product;
    private Date bookingDate;
    private Boolean insurance;
    private String insuranceAmt;
    private String insuranceValue;
    private String service;
    private String shipmentValue;
    private Date invoiceDate;
    private String invoiceNbr;
    private String content;

    private String shipperPersonName;
    private String shipperAddress1;
    private String shipperAddress2;
    private String shipperAddress3;
    private String shipperZipCode;
    private String shipperCity;
    private String shipperState;
    private String shipperPhoneNbr;
    private String shipperEmailAddress;
    private String shipperKycType;
    private String shipperKycNbr;
    private byte[] shipperKycDoc;

    private String receiverAddressBook;
    private String receiverCompany;
    private String receiverPersonName;
    private String receiverAddress1;
    private String receiverAddress2;
    private String receiverAddress3;
    private String receiverZipCode;
    private String receiverCity;
    private String receiverCountry;
    private String receiverState;
    private String receiverPhoneNbr;
    private String receiverPhoneNbr2;
    private String receiverEmailAddress;

    private String pcs;
    private String actualWeight;
    private String volumetricWeight;
    private String consignerWeight;
    private String consignerAmount;
    private String chargeableWeight;
    private String parcelNo;
    private String chargeableEt;

    private String invoiceType;
    private String incoterms;
    private String note;
    private String descNote;

    private String shipmentTotalWeight;
    private String shipmentTotalAmount;
    private String shipperCountry;
    private String shipperCompany;

}
