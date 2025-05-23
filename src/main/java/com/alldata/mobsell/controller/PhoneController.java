package com.alldata.mobsell.controller;

import com.alldata.mobsell.config.WebSocketHandler;
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
import java.util.Optional;

@RestController
@RequestMapping("/api/phones")
public class PhoneController {

    private final PhoneService phoneService;
    private final UserService userService;
    private final WebSocketHandler webSocketHandler;

    @Autowired
    public PhoneController(PhoneService phoneService, UserService userService, WebSocketHandler webSocketHandler) {
        this.phoneService = phoneService;
        this.userService = userService;
        this.webSocketHandler = webSocketHandler;
    }

    @GetMapping
    List<Phone> getPhones() {
        return phoneService.findAll();
    }

    @GetMapping("/make/{make}")
    List<Phone> getPhonesByMake(@PathVariable String make) {
        return phoneService.findByMake(make);
    }

    @GetMapping("/{id}")
    Optional<Phone> getPhone(@PathVariable Long id) {
        return phoneService.findById(id);
    }

    @GetMapping("/user/{id}")
    List<Phone> getPhonesByUserId(@PathVariable Long id) {
        return phoneService.findByUserId(id);
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
        try {
            webSocketHandler.broadcast("A phone has been added to the catalog!:\n - " + phoneDTO.getModel() + " $" + phoneDTO.getPrice());
        } catch (Exception e) {
            throw new RuntimeException("Failed to save phone " + phoneDTO.getMake() + " " +  phoneDTO.getModel());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPhone);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Phone> updatePhone(@PathVariable Long id, @RequestBody PhoneDTO phoneDTO,Authentication authentication) {
        Optional<Phone> phoneOptional = phoneService.findById(id);
        boolean phoneExists = phoneOptional.isPresent();
        if (phoneExists && authentication.getName().equals(phoneOptional.get().getUser().getUsername())) {
            Phone phone = phoneOptional.get();
            phone.setMake(phoneDTO.getMake());
            phone.setModel(phoneDTO.getModel());
            phone.setCpu(phoneDTO.getCpu());
            phone.setRam(phoneDTO.getRam());
            phone.setPrice(phoneDTO.getPrice());
            Phone updatedPhone = phoneService.save(phone);
            return ResponseEntity.ok(updatedPhone);
        } else {
            return phoneExists ? ResponseEntity.status(HttpStatus.FORBIDDEN).build() : ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePhoneById(@PathVariable Long id) {
        phoneService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deletePhoneByUser(@PathVariable Long id) {
        phoneService.deleteByUserId(id);
        return ResponseEntity.ok().build();
    }
}