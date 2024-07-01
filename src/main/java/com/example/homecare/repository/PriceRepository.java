package com.example.homecare.repository;

import com.example.homecare.model.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<Price, String> {
    List<Price> findPricesByCollaborator_CollaboratorIdOrderByType(String collaboratorId);

}
