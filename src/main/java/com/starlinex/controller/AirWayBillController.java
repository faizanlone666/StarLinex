package com.starlinex.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.starlinex.exception.StarLinexException;
import com.starlinex.model.AirWay;
import com.starlinex.model.ServiceResponse;
import com.starlinex.service.impl.AirWayBillServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/airWay")
@RequiredArgsConstructor
public class AirWayBillController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);
    private final AirWayBillServiceImpl airWayBillService;

    @PostMapping("/storeAirWayBill")
    public ResponseEntity<ServiceResponse> saveAirWayBill(@RequestPart("airWay") @NotNull String airWay, @RequestPart("doc") MultipartFile[] file) throws StarLinexException {
        ServiceResponse serviceResponse = new ServiceResponse();
        AirWay airWayModel = new AirWay();
        try {
            if (file != null && airWay != null) {
                ObjectMapper objectMapper = new ObjectMapper();
                airWayModel = objectMapper.readValue(airWay, AirWay.class);
                airWayModel.setShipperKycDoc(file);
                serviceResponse.setResponseCode(200);
                serviceResponse.setResponse(airWayBillService.storeAirWayBillInfo(airWayModel));
                serviceResponse.setMessage("Success");
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e);
            throw new StarLinexException("Failed to store data");
        }
        return ResponseEntity.ok(serviceResponse);

    }

    @GetMapping("/getAllData/{id}")
    public ResponseEntity<ServiceResponse> getAllData(@PathVariable @NotNull Integer id) throws StarLinexException {
        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setResponse(airWayBillService.getDataById(id));
        serviceResponse.setResponseCode(200);
        serviceResponse.setMessage("Success");
        return ResponseEntity.ok(serviceResponse);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable Integer id) throws StarLinexException {
        byte[] image = airWayBillService.downloadImage(id);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(image);
    }

}
