package com.example.homecare.controller;

import com.example.homecare.model.dto.IRequestStatistic;
import com.example.homecare.model.dto.RequestStatisticDto;
import com.example.homecare.service.StatisticService;
import com.example.homecare.util.ResourceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(ResourceUtil.PATH.STATISTIC)
public class StatisticController {
    @Autowired
    private StatisticService statisticService;
    @GetMapping("/status/{month}/{collaboratorId}")
    public ResponseEntity<?> getStatus(@PathVariable Integer month, @PathVariable String collaboratorId) {
        List<IRequestStatistic> requestStatisticDtos = statisticService.getStatusRequestCount(month, collaboratorId);
        return new ResponseEntity<>(requestStatisticDtos, HttpStatus.OK);
    }
}
