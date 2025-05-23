package com.finalproject.gamestop.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.finalproject.gamestop.dto.ConsoleDTO;
import com.finalproject.gamestop.service.ConsoleService;

@RestController
@RequestMapping("/api/consoles")
public class ConsoleController {

    @Autowired
    private ConsoleService consoleService;

    @GetMapping
    public ResponseEntity<List<ConsoleDTO>> getAllConsoles() {
        List<ConsoleDTO> consoles = consoleService.getAllConsoleDTOs();
        return new ResponseEntity<>(consoles, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsoleDTO> getConsoleById(@PathVariable Long id) {
        ConsoleDTO console = consoleService.getConsoleDTOById(id);
        return new ResponseEntity<>(console, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ConsoleDTO> createConsole(@RequestBody ConsoleDTO consoleDTO) {
        ConsoleDTO newConsole = consoleService.createConsole(consoleDTO);
        return new ResponseEntity<>(newConsole, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsoleDTO> updateConsole(@PathVariable Long id, @RequestBody ConsoleDTO consoleDTO) {
        ConsoleDTO updatedConsole = consoleService.updateConsole(id, consoleDTO);
        return new ResponseEntity<>(updatedConsole, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsole(@PathVariable Long id) {
        consoleService.deleteConsole(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Console deleted successfully");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/manufacturer/{manufacturer}")
    public ResponseEntity<List<ConsoleDTO>> getConsolesByManufacturer(@PathVariable String manufacturer) {
        List<ConsoleDTO> consoles = consoleService.findConsoleDTOByManufacturer(manufacturer);
        return new ResponseEntity<>(consoles, HttpStatus.OK);
    }

    @GetMapping("/generation/{generation}")
    public ResponseEntity<List<ConsoleDTO>> getConsolesByGeneration(@PathVariable String generation) {
        List<ConsoleDTO> consoles = consoleService.findConsoleDTOByGeneration(generation);
        return new ResponseEntity<>(consoles, HttpStatus.OK);
    }

    @GetMapping("/price-range")
    public ResponseEntity<List<ConsoleDTO>> getConsolesByPriceRange(
            @RequestParam BigDecimal minPrice,
            @RequestParam BigDecimal maxPrice) {
        List<ConsoleDTO> consoles = consoleService.findConsoleDTOByPriceRange(minPrice, maxPrice);
        return new ResponseEntity<>(consoles, HttpStatus.OK);
    }

    @GetMapping("/popular")
    public ResponseEntity<List<ConsoleDTO>> getPopularConsoles() {
        List<ConsoleDTO> consoles = consoleService.findConsoleDTOByPopularity();
        return new ResponseEntity<>(consoles, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ConsoleDTO>> searchConsolesByName(@RequestParam String name) {
        List<ConsoleDTO> consoles = consoleService.searchConsoleDTOByName(name);
        return new ResponseEntity<>(consoles, HttpStatus.OK);
    }
}