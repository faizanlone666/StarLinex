package com.starlinex.repository;

import com.starlinex.entity.ShipmentDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShipmentDetailsRepository extends JpaRepository<ShipmentDetails, Integer> {
    List<ShipmentDetails> findAllByAwbNbr(String id);

}
