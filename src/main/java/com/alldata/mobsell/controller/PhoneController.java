package com.alldata.mobsell.controller;

import com.alldata.mobsell.dto.PhoneDTO;
import com.alldata.mobsell.model.Phone;
import com.alldata.mobsell.model.User;
import com.alldata.mobsell.service.PhoneService;
import com.alldata.mobsell.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/phones")
public class PhoneController {

    private final PhoneService phoneService;
    private final UserService userService;

    @Autowired
    public PhoneController(PhoneService phoneService, UserService userService) {
        this.phoneService = phoneService;
        this.userService = userService;
    }

    @GetMapping
    List<Phone> getPhones() {
        return phoneService.findAll();
    }

    @PostMapping
    public ResponseEntity<Phone> createPhone(@RequestBody PhoneDTO phoneDTO, Authentication authentication) {
        String username = authentication.getName();
        User currentUser = userService.findByUsername(username);

        Phone phone = new Phone(
            phoneDTO.getMake(),
            phoneDTO.getModel(),
            phoneDTO.getCpu(),
            phoneDTO.getRam(),
            phoneDTO.getPrice(),
            currentUser
        );

        Phone savedPhone = phoneService.save(phone);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPhone);
    }
}