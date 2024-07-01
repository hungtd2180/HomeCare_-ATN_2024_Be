package com.example.homecare.repository;

import com.example.homecare.model.entity.CollaboratorAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CollaboratorAccountRepository extends JpaRepository<CollaboratorAccount, String> {
    Optional<CollaboratorAccount> findByUsername(String username);
    Optional<CollaboratorAccount> findByUsernameAndPassword(String username, String password);
    List<CollaboratorAccount> findCollaboratorAccountsByCollaborator_CollaboratorId(String collaboratorId);
}
