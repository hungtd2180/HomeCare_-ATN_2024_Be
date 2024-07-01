package com.example.homecare.repository;

import com.example.homecare.model.dto.IRating;
import com.example.homecare.model.dto.IRequestStatistic;
import com.example.homecare.model.dto.RequestStatisticDto;
import com.example.homecare.model.entity.Collaborator;
import com.example.homecare.model.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, String> {
    List<Request> findRequestsByUserUserIdAndDeviceDeviceIdOrderByTimeStartDesc(String userId,String deviceId);
    List<Request> findRequestsByUserUserIdOrderByTimeStartDesc(String userId);
    List<Request> findRequestsByUserUserIdAndCollaboratorCollaboratorId(String userId,String collaboratorId);
    List<Request> findRequestsByEmployee_EmployeeIdOrderByTimeStartDesc(String employeeId);
    List<Request> findRequestsByEmployee_EmployeeIdAndStatusRequestContaining(String employeeId,String statusRequest);
    List<Request> findRequestsByCollaboratorCollaboratorIdAndStatusRequest(String collaboratorId,String statusRequest);

    @Query("SELECT r FROM Request r WHERE " +
            "(:startTime IS NULL OR r.timeStart >= :startTime)" +
            " AND (:endTime IS NULL OR r.timeStart <= :endTime) " +
            "AND (:statusRequest IS NULL OR r.statusRequest = :statusRequest)" +
            "AND r.collaborator.collaboratorId = :collaboratorId ORDER BY r.timeStart DESC")
    List<Request> findRequestByTimeAndStatus(@Param("startTime") Long startTime,
                                             @Param("endTime") Long endTime,
                                             @Param("statusRequest") String statusRequest,
                                             @Param("collaboratorId") String collaboratorId);

    @Query("SELECT r FROM Request r WHERE " +
            "(:startTime IS NULL OR r.timeAccept >= :startTime)" +
            " AND (:endTime IS NULL OR r.timeAccept <= :endTime) " +
            "AND (:statusRequest IS NULL OR r.statusRequest = :statusRequest)" +
            "AND r.collaborator.collaboratorId = :collaboratorId ORDER BY r.timeAccept DESC")
    List<Request> findRequestByTimeAccept(@Param("startTime") Long startTime,
                                             @Param("endTime") Long endTime,
                                             @Param("statusRequest") String statusRequest,
                                             @Param("collaboratorId") String collaboratorId);

    @Query("SELECT r FROM Request r WHERE " +
            "(:startTime IS NULL OR r.timeEnd >= :startTime)" +
            " AND (:endTime IS NULL OR r.timeEnd <= :endTime) " +
            "AND (:statusRequest IS NULL OR r.statusRequest = :statusRequest)" +
            "AND r.collaborator.collaboratorId = :collaboratorId ORDER BY r.timeEnd DESC")
    List<Request> findRequestByTimeDone(@Param("startTime") Long startTime,
                                          @Param("endTime") Long endTime,
                                          @Param("statusRequest") String statusRequest,
                                          @Param("collaboratorId") String collaboratorId);

    @Query("SELECT r.statusRequest as statusRequest, COUNT(r) as count FROM Request r " +
            "WHERE (:startTime IS NULL OR r.timeStart >= :startTime) " +
            "AND (:endTime IS NULL OR r.timeStart <= :endTime) " +
            "AND r.collaborator.collaboratorId = :collaboratorId " +
            "GROUP BY r.statusRequest ")
    List<IRequestStatistic> requestStatistic (@Param("startTime") Long startTime,
                                              @Param("endTime") Long endTime,
                                              @Param("collaboratorId") String collaboratorId);

    @Query("SELECT AVG(r.rate) as rate FROM Request r WHERE r.collaborator.collaboratorId = :collaboratorId AND r.rate <> 0")
    IRating getRate(@Param("collaboratorId") String collaboratorId);

    @Query("select r.collaborator from Request r where r.modelName = :model_name and r.statusDevice = '4' group by r.collaborator")
    List<Collaborator> findCollaboratorByModelName(@Param("model_name") String modelName);

    Long countRequestsByTimeStartAfter(String time);
    List<Request> findRequestsByTimeStartBetween(String startTime, String endTime);
}
