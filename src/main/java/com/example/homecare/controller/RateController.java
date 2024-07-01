package com.example.homecare.controller;

import com.example.homecare.model.entity.Rate;
import com.example.homecare.service.RateService;
import com.example.homecare.util.ResourceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping(ResourceUtil.PATH.RATE)
public class RateController {

    @Autowired
    private RateService rateService;

    @GetMapping
    public ResponseEntity<List<Rate>> getAllRates() {
        List<Rate> rates = rateService.getAllRates();
        return new ResponseEntity<>(rates, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rate> getRateById(@PathVariable int id) {
        Optional<Rate> rate = rateService.getRateById(id);
        if (rate.isPresent()) {
            return new ResponseEntity<>(rate.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Rate> createRate(@RequestBody Rate rate) {
        try {
            Rate savedRate = rateService.saveRate(rate);
            return new ResponseEntity<>(savedRate, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rate> updateRate(@PathVariable int id, @RequestBody Rate rate) {
        Optional<Rate> existingRate = rateService.getRateById(id);
        if (existingRate.isPresent()) {
            rate.setRateId(id);
            Rate updatedRate = rateService.saveRate(rate);
            return new ResponseEntity<>(updatedRate, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteRate(@PathVariable int id) {
        try {
            rateService.deleteRate(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}