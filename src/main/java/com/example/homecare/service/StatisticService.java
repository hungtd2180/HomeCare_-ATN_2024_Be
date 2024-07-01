package com.example.homecare.service;

import com.example.homecare.model.dto.IRequestStatistic;
import com.example.homecare.model.dto.RequestStatisticDto;
import com.example.homecare.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticService {
    @Autowired
    private RequestRepository requestRepository;

    public List<IRequestStatistic> getStatusRequestCount(Integer month, String collaboratorId) {
        return requestRepository.requestStatistic(null, null, collaboratorId);
    }
}
