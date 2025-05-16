package com.alldata.mobsell.controller;

import com.alldata.mobsell.model.Phone;
import com.alldata.mobsell.service.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/api/phone")
public class PhoneController {
    private final PhoneRepository phoneRepository;

    @Autowired
    public PhoneController(PhoneRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }

    @GetMapping
    public ResponseEntity<List<Phone>> findAll() {
        List<Phone> phones = phoneRepository.findAll();
        return ResponseEntity.ok(phones);
    }
}
