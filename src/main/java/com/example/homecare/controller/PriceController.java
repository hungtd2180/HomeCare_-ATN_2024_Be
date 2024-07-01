package com.example.homecare.controller;

import com.example.homecare.model.dto.PriceObject;
import com.example.homecare.model.entity.Price;
import com.example.homecare.service.PriceService;
import com.example.homecare.util.ResourceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping(ResourceUtil.PATH.PRICE)
public class PriceController {
    @Autowired
    private PriceService priceService;
    @GetMapping("/collaborator/{id}")
    public ResponseEntity<List<Price>> getPricesByCollaboratorId(@PathVariable String id) {
        return new ResponseEntity<>(priceService.getPriceListByCollaboratorId(id), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Price> getPriceById(@PathVariable String id) {
        Optional<Price> price = priceService.getById(id);
        if (price.isPresent()) {
            return new ResponseEntity<>(price.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Price> savePrice(@RequestBody PriceObject price) {
        try{
            Price savePrice = priceService.savePrice(price);
            return new ResponseEntity<>(savePrice, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Price> updatePrice(@PathVariable String id, @RequestBody PriceObject price) {
        Optional<Price> existingPrice = priceService.getById(id);
        if (existingPrice.isPresent()) {
            if (price.getType() != null){
                existingPrice.get().setType(price.getType());
            }
            if (price.getName() != null){
                existingPrice.get().setName(price.getName());
            }
            existingPrice.get().setPriceHigh(price.getPriceHigh());
            existingPrice.get().setPriceLow(price.getPriceLow());
            Price updatedPrice = priceService.update(existingPrice.get());
            return new ResponseEntity<>(updatedPrice, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePrice(@PathVariable String id) {
        try {
            priceService.deletePrice(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
