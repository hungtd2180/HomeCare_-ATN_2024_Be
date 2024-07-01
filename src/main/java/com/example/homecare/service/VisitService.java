package com.example.homecare.service;

import com.example.homecare.model.entity.Visit;
import com.example.homecare.repository.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.homecare.util.EncoderUtil.createId;

@Service
public class VisitService {
    @Autowired
    private VisitRepository visitRepository;
    public Visit saveVisit(String month, String year){
        Visit visit = new Visit();
        visit.setVisitId(createId("visit"));
        visit.setMonth(month);
        visit.setYear(year);
        return visitRepository.save(visit);
    }
    public Long getVisitCount(String month, String year){
        return visitRepository.countVisit(month, year);
    }
    public Map<Integer, Long> getVisitCountByYear(String year) {
        List<Object[]> results = visitRepository.countVisitByYear(year);
        Map<Integer, Long> visitCountByMonth = new HashMap<>();
        for (int month = 1; month <= 12; month++) {
            visitCountByMonth.put(month, 0L);
        }
        for (Object[] result : results) {
            Integer month = Integer.parseInt(result[0].toString());
            Long count = (Long) result[1];
            visitCountByMonth.put(month, count);
        }

        return visitCountByMonth;
    }
}
