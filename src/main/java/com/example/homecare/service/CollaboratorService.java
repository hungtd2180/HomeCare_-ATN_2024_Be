package com.example.homecare.service;

import com.example.homecare.model.dto.CollaboratorSearchForm;
import com.example.homecare.model.entity.Collaborator;
import com.example.homecare.model.entity.CollaboratorAccount;
import com.example.homecare.repository.CollaboratorAccountRepository;
import com.example.homecare.repository.CollaboratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.homecare.util.DateUtil.getCurrentTime;
import static com.example.homecare.util.DateUtil.getFirstDayOfMonth;
import static com.example.homecare.util.EncoderUtil.createId;
import static com.example.homecare.util.EncoderUtil.encodeBase64Password;

@Service
public class CollaboratorService {

    @Autowired
    private CollaboratorRepository collaboratorRepository;
    @Autowired
    private CollaboratorAccountService collaboratorAccountService;

    public List<Collaborator> getAllCollaborators() {
        return collaboratorRepository.findAll();
    }

    public Optional<Collaborator> getCollaboratorById(String collaboratorId) {
        return collaboratorRepository.findById(collaboratorId);
    }
    public Optional<Collaborator> loginCollaborator(String username, String password) {
        return collaboratorRepository.findByUsernameAndPassword(username, encodeBase64Password(password));
    }

    public List<Collaborator> findCollaborators(CollaboratorSearchForm searchForm) {
        return collaboratorRepository
                .findByAddressContainingAndNameContainingAndFieldContaining
                        (searchForm.getAddress(), searchForm.getName(), searchForm.getField());
    }
    public Collaborator saveCollaborator(Collaborator collaborator) {
        if (collaborator.getCollaboratorId() == null || collaborator.getCollaboratorId().isEmpty()){
            collaborator.setCollaboratorId(createId("collab"));
            collaborator.setTime(getCurrentTime());
            collaborator.setStatus(1);
            collaborator.setImage("Empty.png");
            Optional<CollaboratorAccount> existing = collaboratorAccountService.getCollaboratorAccountByUsername(collaborator.getUsername());
            if (existing.isPresent()) {
                throw new IllegalArgumentException("User already exists");
            }
            collaborator.setPassword(encodeBase64Password(collaborator.getPassword()));

        }
        return collaboratorRepository.save(collaborator);
    }
    public Long countCollaborators() {
        return collaboratorRepository.countCollaboratorsByTimeAfter(getFirstDayOfMonth());
    }
    public void deleteCollaborator(String collaboratorId) {
        collaboratorRepository.deleteById(collaboratorId);
    }
}
