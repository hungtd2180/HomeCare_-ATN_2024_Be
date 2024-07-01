package com.example.homecare.service;

import com.example.homecare.model.dto.IRating;
import com.example.homecare.model.dto.RequestEditForm;
import com.example.homecare.model.dto.RequestForm;
import com.example.homecare.model.entity.*;
import com.example.homecare.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.homecare.util.DateUtil.getCurrentTime;
import static com.example.homecare.util.DateUtil.getFirstDayOfMonth;
import static com.example.homecare.util.EncoderUtil.createId;

@Service
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;
    @Autowired
    private CollaboratorService collaboratorService;
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private UserService userService;
    @Autowired
    private EmployeeService employeeService;

    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }

    public Optional<Request> getRequestById(String requestId) {
        return requestRepository.findById(requestId);
    }
    public List<Request> getRequestListByUserIdAndDeviceId(String userId, String deviceId) {
//        return requestRepository.findRequestsByUserIdAndDeviceId(userId, deviceId);
        return requestRepository.findRequestsByUserUserIdAndDeviceDeviceIdOrderByTimeStartDesc(userId, deviceId);
    }
    public List<Request> getRequestByUserId(String userId){
        return requestRepository.findRequestsByUserUserIdOrderByTimeStartDesc(userId);
    }
    public List<Request> getRequestByCustomerId(String customerId, String collaboratorId){
        return requestRepository.findRequestsByUserUserIdAndCollaboratorCollaboratorId(customerId, collaboratorId);
    }
    public List<Request> getRequestByEmployeeAndStatus(String employeeId, String status){
        return requestRepository.findRequestsByEmployee_EmployeeIdAndStatusRequestContaining(employeeId, status);
    }
    public List<Request> getRequestByEmployee(String employeeId){
        return requestRepository.findRequestsByEmployee_EmployeeIdOrderByTimeStartDesc(employeeId);
    }
    public List<Request> getRequestByTimeAndStatus(Long timeStart, Long timeEnd, String status, String collaboratorId){
        return requestRepository.findRequestByTimeAndStatus(timeStart, timeEnd, status, collaboratorId);
    }
    public List<Request> getRequestByTimeAccept(Long timeStart, Long timeEnd, String collaboratorId){
        return requestRepository.findRequestByTimeAccept(timeStart, timeEnd, "2", collaboratorId);
    }
    public List<Request> getRequestByTimeDone(Long timeStart, Long timeEnd, String collaboratorId){
        return requestRepository.findRequestByTimeDone(timeStart, timeEnd, "3", collaboratorId);
    }
    public Request saveRequest(RequestForm form) {
        Request request = new Request();
        request.setRequestId(createId("request"));
        request.setRate(0);
        if (form.getUserId() != null) {
            Optional<User> user = userService.getUserById(form.getUserId());
            request.setUser(user.get());
        }
        if (form.getCollaboratorId() != null) {
            Optional<Collaborator> collaborator = collaboratorService.getCollaboratorById(form.getCollaboratorId());
            request.setCollaborator(collaborator.get());
        }
        if (form.getDeviceId() != null) {
            Optional<Device> device = deviceService.getDeviceById(form.getDeviceId());
            request.setDevice(device.get());
        }
        form.setTimeStart(getCurrentTime());
        request.fromDto(form);
        return requestRepository.save(request);
    }
    public Request editRequest(String requestId, RequestEditForm form) {
        Request request = requestRepository.findById(requestId).get();
        if (request.getEmployee() != null) {
            List<Request> listRequest = requestRepository.findRequestsByEmployee_EmployeeIdAndStatusRequestContaining(request.getEmployee().getEmployeeId(), "2");
            if (listRequest.size() == 1) {
                request.getEmployee().setStatus("2");
            }
        }
        if (form.getEmployeeId() != null) {
            Employee employee = employeeService.getEmployeeById(form.getEmployeeId()).get();
            request.setEmployee(employee);
        }
        if (form.getFixDescription() != null) {
            request.setFixDescription(form.getFixDescription());
        }
        if (form.getStatusDevice() != null) {
            request.setStatusDevice(form.getStatusDevice());
        }
        return requestRepository.save(request);

    }
    public Request editRate(String requestId, Integer rate) {
        Request request = requestRepository.findById(requestId).get();
        request.setRate(rate);
        return requestRepository.save(request);
    }
    public IRating getRating(String collaboratorId) {
        return requestRepository.getRate(collaboratorId);
    }
    public Request editStatus(String requestId, String status){
        Request request = requestRepository.findById(requestId).get();
        switch (status){
            case "0":
            {
                request.setStatusRequest("0");
                request.setStatusDevice("3b");
                request.setTimeEnd(getCurrentTime());
                break;
            }
            case "1":
            {
                request.setStatusRequest("2");
                request.setStatusDevice("2");
                request.setTimeAccept(getCurrentTime());
                break;
            }
            case "2":{
                request.setStatusDevice("3a");
                break;
            }
            case "3":{
                request.setStatusDevice("3b");
                break;
            }
            case "4":
            {
                if (!request.getStatusRequest().equals("0")){
                    request.setStatusRequest("3");
                }
                if (request.getStatusDevice().equals("3a")) {
                    request.setStatusDevice("4");
                }
                else if (request.getStatusDevice().equals("3b")){
                    request.setStatusDevice("0");
                }
                request.setTimeEnd(getCurrentTime());
                break;
            }
        }
        return requestRepository.save(request);
    }
    public void deleteRequest(String requestId) {
        requestRepository.deleteById(requestId);
    }
    public List<Collaborator> getCollaboratorsByModelName(String modelName) {
        return requestRepository.findCollaboratorByModelName(modelName);
    }
    public Long countRequest(){
        return requestRepository.countRequestsByTimeStartAfter(getFirstDayOfMonth());
    }
    public List<Request> requestByMonth(String timeStart, String timeEnd){
        return requestRepository.findRequestsByTimeStartBetween(timeStart, timeEnd);
    }
}
