package com.example.homecare.controller;

import com.example.homecare.model.dto.CollaboratorSearchForm;
import com.example.homecare.model.entity.Collaborator;
import com.example.homecare.service.CollaboratorService;
import com.example.homecare.util.ResourceUtil;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping(ResourceUtil.PATH.COLLABORATOR)
public class CollaboratorController {

    @Autowired
    private CollaboratorService collaboratorService;

    @GetMapping
    public ResponseEntity<List<Collaborator>> getAllCollaborators() {
        List<Collaborator> collaborators = collaboratorService.getAllCollaborators();
        return new ResponseEntity<>(collaborators, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Collaborator> getCollaboratorById(@PathVariable String id) {
        Optional<Collaborator> collaborator = collaboratorService.getCollaboratorById(id);
        if (collaborator.isPresent()) {
            return new ResponseEntity<>(collaborator.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/new")
    public ResponseEntity<Long> getNewCollaboratorId() {
        Long count = collaboratorService.countCollaborators();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
    @PostMapping("/search")
    public ResponseEntity<List<Collaborator>> findCollaborators(@RequestBody CollaboratorSearchForm searchForm) {
        searchForm.defaultData();
        List<Collaborator> collaborator = collaboratorService.findCollaborators(searchForm);
        return new ResponseEntity<>(collaborator, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Collaborator> createCollaborator(@RequestBody Collaborator collaborator) {
        try {
            Collaborator savedCollaborator = collaboratorService.saveCollaborator(collaborator);
            return new ResponseEntity<>(savedCollaborator, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Collaborator> updateCollaborator(@PathVariable String id, @RequestBody Collaborator collaborator) {
        Optional<Collaborator> existing = collaboratorService.getCollaboratorById(id);
        if (existing.isPresent()) {
            collaborator.setCollaboratorId(id);
            if (collaborator.getName() != null) {
                existing.get().setName(collaborator.getName());
            }
            if (collaborator.getEmail() != null) {
                existing.get().setEmail(collaborator.getEmail());
            }
            if (collaborator.getPhone() != null) {
                existing.get().setPhone(collaborator.getPhone());
            }
            if (collaborator.getAddress() != null) {
                existing.get().setAddress(collaborator.getAddress());
            }
            if (collaborator.getPassword() != null) {
                existing.get().setPassword(collaborator.getPassword());
            }
            if (collaborator.getImage() != null) {
                existing.get().setImage(collaborator.getImage().split("\\\\")[2]);
            }
            if (collaborator.getDescription() != null) {
                existing.get().setDescription(collaborator.getDescription());
            }
            if (collaborator.getTimeStart() != null) {
                existing.get().setTimeStart(collaborator.getTimeStart());
            }
            if (collaborator.getTimeEnd() != null) {
                existing.get().setTimeEnd(collaborator.getTimeEnd());
            }
            if (collaborator.getField() != null) {
                existing.get().setField(collaborator.getField());
            }
            if (collaborator.getStatus() != null) {
                existing.get().setStatus(collaborator.getStatus());
            }
            Collaborator updatedCollaborator = collaboratorService.saveCollaborator(existing.get());
            return new ResponseEntity<>(updatedCollaborator, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCollaborator(@PathVariable String id) {
        try {
            collaboratorService.deleteCollaborator(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}