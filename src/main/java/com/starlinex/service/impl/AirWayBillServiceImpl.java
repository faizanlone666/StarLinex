package com.starlinex.service.impl;

import com.starlinex.entity.AirWayBill;
import com.starlinex.entity.ShipmentDetails;
import com.starlinex.entity.SpecialService;
import com.starlinex.entity.WeightAndDimensions;
import com.starlinex.exception.StarLinexException;
import com.starlinex.model.AirWay;
import com.starlinex.model.ShipmentData;
import com.starlinex.model.SpecialServiceDetails;
import com.starlinex.model.WeightAndDimensionDetails;
import com.starlinex.repository.AirWayBillRepository;
import com.starlinex.repository.ShipmentDetailsRepository;
import com.starlinex.repository.SpecialServiceRepository;
import com.starlinex.repository.WeightAndDimensionRepository;
import com.starlinex.service.AirWayBillService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AirWayBillServiceImpl implements AirWayBillService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AirWayBillServiceImpl.class);
    private final AirWayBillRepository airWayBillRepository;
    private final ShipmentDetailsRepository shipmentDetailsRepository;
    private final SpecialServiceRepository specialServiceRepository;
    private final WeightAndDimensionRepository weightAndDimensionRepository;
    @Value("${doc.path}")
    private String path;

    @Transactional
    @Override
    public String storeAirWayBillInfo(AirWay airWay) throws StarLinexException {
        try{
            List<String> filePath = new ArrayList<>();
//            File file = new File(path);
//            if(!file.exists()){
//                file.mkdir();
//            }
//            Arrays.stream(airWay.getShipperKycDoc()).forEach(doc->{
//                filePath.add(path + "/" + doc.getOriginalFilename());
//                try {
//                    Files.copy(doc.getInputStream(), Paths.get(path + File.separator + doc.getOriginalFilename()));
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            });
            var airWayBill = AirWayBill.builder()
                    .userId(airWay.getUserId())
            .awbNbr(airWay.getAwbNbr())
            .destination(airWay.getDestination())
            .product(airWay.getProduct())
            .bookingDate(airWay.getBookingDate())
            .insurance(airWay.getInsurance())
            .insuranceAmt(airWay.getInsuranceAmt())
             .insuranceValue(airWay.getInsuranceValue())
            .service(airWay.getService())
            .shipmentValue(airWay.getShipmentValue())
            .invoiceDate(airWay.getInvoiceDate())
            .invoiceNbr(airWay.getInvoiceNbr())
            .content(airWay.getContent())

            .shipperPersonName(airWay.getShipperPersonName())
            .shipperAddress1(airWay.getShipperAddress1())
            .shipperAddress2(airWay.getShipperAddress2())
            .shipperAddress3(airWay.getShipperAddress3())
            .shipperZipCode(airWay.getShipperZipCode())
            .shipperCity(airWay.getShipperCity())
            .shipperState(airWay.getShipperState())
            .shipperPhoneNbr(airWay.getShipperPhoneNbr())
            .shipperEmailAddress(airWay.getShipperEmailAddress())
            .shipperKycType(airWay.getShipperKycType())
            .shipperKycNbr(airWay.getShipperKycNbr())
            .shipperKycDoc(filePath)

            .receiverAddressBook(airWay.getReceiverAddressBook())
            .receiverCompany(airWay.getReceiverCompany())
            .receiverPersonName(airWay.getReceiverPersonName())
            .receiverAddress1(airWay.getReceiverAddress1())
            .receiverAddress2(airWay.getReceiverAddress2())
            .receiverAddress3(airWay.getReceiverAddress3())
            .receiverZipCode(airWay.getReceiverZipCode())
            .receiverCity(airWay.getReceiverCity())
            .receiverCountry(airWay.getReceiverCountry())
            .receiverState(airWay.getReceiverState())
            .receiverPhoneNbr(airWay.getReceiverPhoneNbr())
            .receiverPhoneNbr2(airWay.getReceiverPhoneNbr2())
            .receiverEmailAddress(airWay.getReceiverEmailAddress())

            .pcs(airWay.getPcs())
            .actualWeight(airWay.getActualWeight())
            .volumetricWeight(airWay.getVolumetricWeight())
            .consignerWeight(airWay.getConsignerWeight())
            .chargeableWeight(airWay.getChargeableWeight())
            .parcelNo(airWay.getParcelNo())
            .chargeableEt(airWay.getChargeableEt())

            .invoiceType(airWay.getInvoiceType())
            .incoterms(airWay.getIncoterms())
            .note(airWay.getNote())
            .descNote(airWay.getDescNote())
            .shipmentTotalAmount(airWay.getShipmentTotalAmount())
            .shipmentTotalWeight(airWay.getShipmentTotalWeight())
            .build();
            List<ShipmentData> shipmentDetailsList = airWay.getShipmentDetailsList();
            List<SpecialServiceDetails> specialServicesList = airWay.getSpecialServices();
            List<WeightAndDimensionDetails> weightAndDimensionsList = airWay.getWeightAndDimensions();
            shipmentDetailsList.forEach(list->{
                var shipmentDetails = ShipmentDetails.builder()
                        .userId(airWay.getUserId())
                        .shipmentBoxNo(list.getShipmentBoxNo())
                        .shipmentDescription(list.getShipmentDescription())
                        .shipmentAmount(list.getShipmentAmount())
                        .shipmentIgst(list.getShipmentIgst())
                        .shipmentHsCode(list.getShipmentHsCode())
                        .shipmentUnityType(list.getShipmentUnityType())
                        .shipmentUnitWeight(list.getShipmentUnitWeight())
                        .shipmentUnitRates(list.getShipmentUnitRates())
                        .shipmentQuantity(list.getShipmentQuantity())
                                .build();
                shipmentDetailsRepository.save(shipmentDetails);
            });
            specialServicesList.forEach(details->{
                var specialService = SpecialService.builder()
                        .userId(airWay.getUserId())
                        .specialServiceName(details.getSpecialServiceName())
                        .specialServicePcs(details.getSpecialServicePcs())
                        .build();
                specialServiceRepository.save(specialService);
            });
            weightAndDimensionsList.forEach(list->{
                var weightAndDimension = WeightAndDimensions.builder()
                        .userId(airWay.getUserId())
                        .boxNo(list.getBoxNo())
                        .actualWt(list.getActualWt())
                        .lcm(list.getLcm())
                        .bcm(list.getBcm())
                        .hcm(list.getHcm())
                        .volumetricWt(list.getVolumetricWt())
                        .build();
                weightAndDimensionRepository.save(weightAndDimension);
            });
            airWayBillRepository.save(airWayBill);
        }catch (Exception e){
            LOGGER.error(e.getMessage(),e);
            throw new StarLinexException(e.getMessage());
        }
        return "Data added successfully";
    }

    @Override
    public List<AirWay> getDataById(Integer id) throws StarLinexException{
        List<AirWay> airWays = new ArrayList<>();
        List<AirWayBill> airWayBills;
        List<SpecialService> specialServices;
        List<WeightAndDimensions> weightAndDimensions;
        List<ShipmentDetails> shipmentDetails;
        try{
            airWayBills = airWayBillRepository.findAllByUserId(id);
            specialServices = specialServiceRepository.findAllByUserId(id);
            weightAndDimensions = weightAndDimensionRepository.findAllByUserId(id);
            shipmentDetails = shipmentDetailsRepository.findAllByUserId(id);
            List<SpecialServiceDetails> specialServiceDetails = new ArrayList<>();
            specialServices.forEach(details->{
                var specialService = SpecialServiceDetails.builder()
                        .specialServiceName(details.getSpecialServiceName())
                        .specialServicePcs(details.getSpecialServicePcs())
                        .build();
                specialServiceDetails.add(specialService);
            });
            List<WeightAndDimensionDetails> weightAndDimensionDetails = new ArrayList<>();
            weightAndDimensions.forEach(list->{
                var weightAndDimension = WeightAndDimensionDetails.builder()
                        .actualWt(list.getActualWt())
                        .lcm(list.getLcm())
                        .bcm(list.getBcm())
                        .hcm(list.getHcm())
                        .volumetricWt(list.getVolumetricWt())
                        .build();
                weightAndDimensionDetails.add(weightAndDimension);
            });
            List<ShipmentData> shipmentData = new ArrayList<>();
            shipmentDetails.forEach(list->{
                var shipmentDetailsList = ShipmentData.builder()
                        .shipmentBoxNo(list.getShipmentBoxNo())
                        .shipmentDescription(list.getShipmentDescription())
                        .shipmentAmount(list.getShipmentAmount())
                        .shipmentIgst(list.getShipmentIgst())
                        .shipmentHsCode(list.getShipmentHsCode())
                        .shipmentUnityType(list.getShipmentUnityType())
                        .shipmentUnitWeight(list.getShipmentUnitWeight())
                        .shipmentUnitRates(list.getShipmentUnitRates())
                        .shipmentQuantity(list.getShipmentQuantity())
                        .build();
                shipmentData.add(shipmentDetailsList);
            });
            airWayBills.forEach(airWay->{
                var airWayBill = AirWay.builder()
                        .userId(airWay.getUserId())
                        .awbNbr(airWay.getAwbNbr())
                        .destination(airWay.getDestination())
                        .product(airWay.getProduct())
                        .bookingDate(airWay.getBookingDate())
                        .insurance(airWay.getInsurance())
                        .insuranceAmt(airWay.getInsuranceAmt())
                        .insuranceValue(airWay.getInsuranceValue())
                        .service(airWay.getService())
                        .shipmentValue(airWay.getShipmentValue())
                        .invoiceDate(airWay.getInvoiceDate())
                        .invoiceNbr(airWay.getInvoiceNbr())
                        .content(airWay.getContent())

                        .shipperPersonName(airWay.getShipperPersonName())
                        .shipperAddress1(airWay.getShipperAddress1())
                        .shipperAddress2(airWay.getShipperAddress2())
                        .shipperAddress3(airWay.getShipperAddress3())
                        .shipperZipCode(airWay.getShipperZipCode())
                        .shipperCity(airWay.getShipperCity())
                        .shipperState(airWay.getShipperState())
                        .shipperPhoneNbr(airWay.getShipperPhoneNbr())
                        .shipperEmailAddress(airWay.getShipperEmailAddress())
                        .shipperKycType(airWay.getShipperKycType())
                        .shipperKycNbr(airWay.getShipperKycNbr())

                        .receiverAddressBook(airWay.getReceiverAddressBook())
                        .receiverCompany(airWay.getReceiverCompany())
                        .receiverPersonName(airWay.getReceiverPersonName())
                        .receiverAddress1(airWay.getReceiverAddress1())
                        .receiverAddress2(airWay.getReceiverAddress2())
                        .receiverAddress3(airWay.getReceiverAddress3())
                        .receiverZipCode(airWay.getReceiverZipCode())
                        .receiverCity(airWay.getReceiverCity())
                        .receiverCountry(airWay.getReceiverCountry())
                        .receiverState(airWay.getReceiverState())
                        .receiverPhoneNbr(airWay.getReceiverPhoneNbr())
                        .receiverPhoneNbr2(airWay.getReceiverPhoneNbr2())
                        .receiverEmailAddress(airWay.getReceiverEmailAddress())

                        .pcs(airWay.getPcs())
                        .actualWeight(airWay.getActualWeight())
                        .volumetricWeight(airWay.getVolumetricWeight())
                        .consignerWeight(airWay.getConsignerWeight())
                        .chargeableWeight(airWay.getChargeableWeight())
                        .parcelNo(airWay.getParcelNo())
                        .chargeableEt(airWay.getChargeableEt())

                        .invoiceType(airWay.getInvoiceType())
                        .incoterms(airWay.getIncoterms())
                        .note(airWay.getNote())
                        .descNote(airWay.getDescNote())
                        .shipmentTotalAmount(airWay.getShipmentTotalAmount())
                        .shipmentTotalWeight(airWay.getShipmentTotalWeight())
                        .specialServices(specialServiceDetails)
                        .shipmentDetailsList(shipmentData)
                        .weightAndDimensions(weightAndDimensionDetails)
                        .build();
                airWays.add(airWayBill);
            });


        }catch (Exception e){
            LOGGER.error(e.getMessage(),e);
            throw new StarLinexException("Data not Found");
        }
        return airWays;
    }
}
