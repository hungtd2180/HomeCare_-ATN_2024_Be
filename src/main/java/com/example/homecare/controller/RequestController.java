package com.example.homecare.controller;

import com.example.homecare.model.dto.IRating;
import com.example.homecare.model.dto.RequestEditForm;
import com.example.homecare.model.dto.RequestForm;
import com.example.homecare.model.dto.TimeSearchForm;
import com.example.homecare.model.entity.Collaborator;
import com.example.homecare.model.entity.Request;
import com.example.homecare.service.RequestService;
import com.example.homecare.util.DateUtil;
import com.example.homecare.util.ResourceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping(ResourceUtil.PATH.REQUEST)
public class RequestController {

    @Autowired
    private RequestService requestService;

    @GetMapping
    public ResponseEntity<List<Request>> getAllRequests() {
        List<Request> requests = requestService.getAllRequests();
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Request> getRequestById(@PathVariable String id) {
        Optional<Request> request = requestService.getRequestById(id);
        if (request.isPresent()) {
            return new ResponseEntity<>(request.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/device/{userId}/{deviceId}")
    public ResponseEntity<List<Request>> getRequestByUserAndDevice(@PathVariable String userId,@PathVariable String deviceId) {
        List<Request> requests = requestService.getRequestListByUserIdAndDeviceId(userId, deviceId);
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Request>> getRequestByUserId(@PathVariable String userId) {
        List<Request> requests = requestService.getRequestByUserId(userId);
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }
    @GetMapping("collaborator/{userId}/{collaboratorId}")
    public ResponseEntity<List<Request>> getRequestByCustomer(@PathVariable String userId, @PathVariable String collaboratorId){
        List<Request> requests = requestService.getRequestByCustomerId(userId, collaboratorId);
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }
    @GetMapping("employee/{employeeId}")
    public ResponseEntity<List<Request>> getRequestByEmployee(@PathVariable String employeeId){
        List<Request> requests = requestService.getRequestByEmployee(employeeId);
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }
    @GetMapping("rate/{id}")
    public ResponseEntity<IRating> getRating(@PathVariable String id){
        IRating rating = requestService.getRating(id);
        return new ResponseEntity<>(rating, HttpStatus.OK);
    }
    @GetMapping("model/{name}")
    public ResponseEntity<List<Collaborator>> getByModel(@PathVariable String name){
        List<Collaborator> collaborators = requestService.getCollaboratorsByModelName(name);
        return new ResponseEntity<>(collaborators, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> createRequest(@RequestBody RequestForm request) {
        try {
            Request savedRequest = requestService.saveRequest(request);
            return new ResponseEntity<>(savedRequest, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("search/{id}/{status}")
    public ResponseEntity<List<Request>> searchRequest(@PathVariable String id, @PathVariable String status, @RequestBody TimeSearchForm form) {
        if (form.getTimeEnd() != null){
            form.setTimeEnd(form.getTimeEnd() + 86399999L);
        }
        List<Request> requests = requestService.getRequestByTimeAndStatus(form.getTimeStart(), form.getTimeEnd(), status, id);
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }
    @PostMapping("search/{id}/accept")
    public ResponseEntity<List<Request>> searchRequestAccept(@PathVariable String id, @RequestBody TimeSearchForm form) {
        if (form.getTimeEnd() != null){
            form.setTimeEnd(form.getTimeEnd() + 86399999L);
        }
        List<Request> requests = requestService.getRequestByTimeAccept(form.getTimeStart(), form.getTimeEnd(), id);
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }
    @PostMapping("search/{id}/done")
    public ResponseEntity<List<Request>> searchRequestDone(@PathVariable String id, @RequestBody TimeSearchForm form) {
        if (form.getTimeEnd() != null){
            form.setTimeEnd(form.getTimeEnd() + 86399999L);
        }
        List<Request> requests = requestService.getRequestByTimeDone(form.getTimeStart(), form.getTimeEnd(), id);
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Request> updateRequest(@PathVariable String id, @RequestBody RequestEditForm form) {
        Optional<Request> existingRequest = requestService.getRequestById(id);
        if (existingRequest.isPresent()) {
            Request updatedRequest = requestService.editRequest(id, form);
            return new ResponseEntity<>(updatedRequest, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/{id}/rate/{rate}")
    public ResponseEntity<Request> updateRate(@PathVariable String id, @PathVariable Integer rate ){
        Optional<Request> existingRequest = requestService.getRequestById(id);
        if (existingRequest.isPresent()) {
            Request update = requestService.editRate(id, rate);
            return new ResponseEntity<>(update, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/{id}/status/{status}")
    public ResponseEntity<Request> updateStatus(@PathVariable String id, @PathVariable String status ){
        try{
            Request request = requestService.editStatus(id, status);
            return new ResponseEntity<>(request, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteRequest(@PathVariable String id) {
        try {
            requestService.deleteRequest(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/new")
    public ResponseEntity<Long> getNew(){
        Long request = requestService.countRequest();
        return new ResponseEntity<>(request, HttpStatus.OK);
    }
    @GetMapping("/month/{id}")
    public ResponseEntity<List<Request>> getRequestByMonth(@PathVariable Long id){
        LocalDate dateNow = LocalDate.now();
        int year = dateNow.getYear();
        if (id > dateNow.getMonthValue()){
            year--;
        }
        YearMonth yearMonth = YearMonth.of(year, id.intValue());
        Long timeStart = yearMonth.atDay(1).atStartOfDay().toEpochSecond(java.time.ZoneOffset.UTC) * 1000;
        Long timeEnd = yearMonth.atEndOfMonth().atTime(23,59,59).toEpochSecond(java.time.ZoneOffset.UTC) * 1000;
        List<Request> requests = requestService.requestByMonth(timeStart.toString(), timeEnd.toString());
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }

}
