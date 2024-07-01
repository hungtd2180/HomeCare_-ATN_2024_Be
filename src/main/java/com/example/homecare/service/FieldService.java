package com.example.homecare.service;

import com.example.homecare.model.entity.Field;
import com.example.homecare.repository.FieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FieldService {

    @Autowired
    private FieldRepository fieldRepository;

    public List<Field> getAllFields() {
        return fieldRepository.findAll();
    }

    public Optional<Field> getFieldById(Integer fieldId) {
        return fieldRepository.findById(fieldId);
    }

    public Field saveField(Field field) {
        return fieldRepository.save(field);
    }

    public void deleteField(Integer fieldId) {
        fieldRepository.deleteById(fieldId);
    }
}