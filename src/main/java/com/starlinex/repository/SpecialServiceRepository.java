package com.starlinex.repository;

import com.starlinex.entity.SpecialService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpecialServiceRepository extends JpaRepository<SpecialService,Integer> {
    List<SpecialService> findAllByAwbNbr(String id);
}
