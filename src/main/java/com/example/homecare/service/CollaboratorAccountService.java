package com.example.homecare.service;

import com.example.homecare.model.entity.Collaborator;
import com.example.homecare.model.entity.CollaboratorAccount;
import com.example.homecare.repository.CollaboratorAccountRepository;
import com.example.homecare.repository.CollaboratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

import static com.example.homecare.util.EncoderUtil.createId;
import static com.example.homecare.util.EncoderUtil.encodeBase64Password;
@Service
public class CollaboratorAccountService {
    @Autowired
    private CollaboratorAccountRepository collaboratorAccountRepository;
    @Autowired
    private CollaboratorRepository collaboratorRepository;
    public Optional<CollaboratorAccount> getCollaboratorAccountByUsername(String username){
        return collaboratorAccountRepository.findByUsername(username);
    }
    public List<CollaboratorAccount> getAllByCollaboratorId(String collaboratorId){
        return collaboratorAccountRepository.findCollaboratorAccountsByCollaborator_CollaboratorId(collaboratorId);
    }
    public Optional<CollaboratorAccount> getCollaboratorAccountByUsernameandPassword(String username, String password){
        return collaboratorAccountRepository.findByUsernameAndPassword(username, encodeBase64Password(password));
    }
    public CollaboratorAccount saveCollaboratorAccount(String username, String password, String collaboratorId){
        Optional<CollaboratorAccount> existing = collaboratorAccountRepository.findByUsername(username);
        if (existing.isPresent()){
            throw new IllegalArgumentException("Already username exists");
        }
        CollaboratorAccount account = new CollaboratorAccount();
        account.setCollaboratorAccountId(createId("account"));
        account.setUsername(username);
        account.setPassword(encodeBase64Password(password));
        Optional<Collaborator> collaborator = collaboratorRepository.findById(collaboratorId);
        account.setCollaborator(collaborator.get());
        return collaboratorAccountRepository.save(account);
    }
    public void delete(String collaboratorAccountId){
        collaboratorAccountRepository.deleteById(collaboratorAccountId);
    }
}
