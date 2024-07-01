package com.example.homecare.service;

import com.example.homecare.model.entity.Rate;
import com.example.homecare.repository.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RateService {

    @Autowired
    private RateRepository rateRepository;

    public List<Rate> getAllRates() {
        return rateRepository.findAll();
    }

    public Optional<Rate> getRateById(Integer rateId) {
        return rateRepository.findById(rateId);
    }

    public Rate saveRate(Rate rate) {
        return rateRepository.save(rate);
    }

    public void deleteRate(Integer rateId) {
        rateRepository.deleteById(rateId);
    }
}
