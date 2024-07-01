package com.example.homecare.controller;

import com.example.homecare.model.entity.Field;
import com.example.homecare.service.FieldService;
import com.example.homecare.util.ResourceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping(ResourceUtil.PATH.FIELD)
public class FieldController {

    @Autowired
    private FieldService fieldService;

    @GetMapping
    public ResponseEntity<List<Field>> getAllFields() {
        List<Field> fields = fieldService.getAllFields();
        return new ResponseEntity<>(fields, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Field> getFieldById(@PathVariable int id) {
        Optional<Field> field = fieldService.getFieldById(id);
        if (field.isPresent()) {
            return new ResponseEntity<>(field.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Field> createField(@RequestBody Field field) {
        try {
            Field savedField = fieldService.saveField(field);
            return new ResponseEntity<>(savedField, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Field> updateField(@PathVariable int id, @RequestBody Field field) {
        Optional<Field> existingField = fieldService.getFieldById(id);
        if (existingField.isPresent()) {
            field.setFieldId(id);
            Field updatedField = fieldService.saveField(field);
            return new ResponseEntity<>(updatedField, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteField(@PathVariable int id) {
        try {
            fieldService.deleteField(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
