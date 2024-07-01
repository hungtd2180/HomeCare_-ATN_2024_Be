package com.example.homecare.repository;

import com.example.homecare.model.entity.Collaborator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CollaboratorRepository extends JpaRepository<Collaborator, String> {
    List<Collaborator> findByAddressContainingAndNameContainingAndFieldContaining(String address, String name, String field);
    Optional<Collaborator> findByUsername(String username);
    Optional<Collaborator> findByUsernameAndPassword(String username, String password);

    Long countCollaboratorsByTimeAfter(String time);

}

