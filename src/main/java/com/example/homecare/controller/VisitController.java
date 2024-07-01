package com.example.homecare.controller;

import com.example.homecare.service.VisitService;
import com.example.homecare.util.ResourceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping(ResourceUtil.PATH.VISIT)
public class VisitController {
    @Autowired
    private VisitService visitService;
    @GetMapping("/{month}/{year}")
    public ResponseEntity<Long> countVisit(@PathVariable String month, @PathVariable String year) {
        Long count = visitService.getVisitCount(month, year);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
    @GetMapping("/year/{year}")
    public Map<Integer, Long> getVisitCountByYear(@PathVariable String year) {
        return visitService.getVisitCountByYear(year);
    }
    @PostMapping("/{month}/{year}")
    public ResponseEntity<?> createVisit(@PathVariable String month, @PathVariable String year) {
        try {
            visitService.saveVisit(month, year);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
