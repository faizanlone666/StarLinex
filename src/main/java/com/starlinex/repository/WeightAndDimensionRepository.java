package com.starlinex.repository;

import com.starlinex.entity.WeightAndDimensions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WeightAndDimensionRepository extends JpaRepository<WeightAndDimensions, Integer> {
    List<WeightAndDimensions> findAllByAwbNbr(String id);
}
