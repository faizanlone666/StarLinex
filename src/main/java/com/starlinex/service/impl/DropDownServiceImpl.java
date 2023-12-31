package com.starlinex.service.impl;

import com.starlinex.entity.CountryData;
import com.starlinex.exception.StarLinexException;
import com.starlinex.repository.CountryDataRepository;
import com.starlinex.service.DropDownService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DropDownServiceImpl implements DropDownService {

    private final CountryDataRepository countryDataRepository;
    @Override
    public List<CountryData> getDropDownById() throws StarLinexException {
        List<CountryData> countryData;
        try {
             countryData = countryDataRepository.findAll();
        }catch (Exception e){
            throw new StarLinexException("Something went wrong.");
        }
        return countryData;
    }
}
