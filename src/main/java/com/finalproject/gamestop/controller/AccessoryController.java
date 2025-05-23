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

import com.finalproject.gamestop.dto.AccessoryDTO;
import com.finalproject.gamestop.service.AccessoryService;

@RestController
@RequestMapping("/api/accessories")
public class AccessoryController {

    @Autowired
    private AccessoryService accessoryService;

    @GetMapping
    public ResponseEntity<List<AccessoryDTO>> getAllAccessories() {
        List<AccessoryDTO> accessories = accessoryService.getAllAccessoryDTOs();
        return new ResponseEntity<>(accessories, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccessoryDTO> getAccessoryById(@PathVariable Long id) {
        AccessoryDTO accessory = accessoryService.getAccessoryDTOById(id);
        return new ResponseEntity<>(accessory, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AccessoryDTO> createAccessory(@RequestBody AccessoryDTO accessoryDTO) {
        AccessoryDTO newAccessory = accessoryService.createAccessory(accessoryDTO);
        return new ResponseEntity<>(newAccessory, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccessoryDTO> updateAccessory(@PathVariable Long id, @RequestBody AccessoryDTO accessoryDTO) {
        AccessoryDTO updatedAccessory = accessoryService.updateAccessory(id, accessoryDTO);
        return new ResponseEntity<>(updatedAccessory, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteAccessory(@PathVariable Long id) {
        accessoryService.deleteAccessory(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Accessory deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<AccessoryDTO>> getAccessoriesByType(@PathVariable String type) {
        List<AccessoryDTO> accessories = accessoryService.findAccessoryDTOByType(type);
        return new ResponseEntity<>(accessories, HttpStatus.OK);
    }

    @GetMapping("/compatibility/{compatibility}")
    public ResponseEntity<List<AccessoryDTO>> getAccessoriesByCompatibility(@PathVariable String compatibility) {
        List<AccessoryDTO> accessories = accessoryService.findAccessoryDTOByCompatibility(compatibility);
        return new ResponseEntity<>(accessories, HttpStatus.OK);
    }

    @GetMapping("/brand/{brand}")
    public ResponseEntity<List<AccessoryDTO>> getAccessoriesByBrand(@PathVariable String brand) {
        List<AccessoryDTO> accessories = accessoryService.findAccessoryDTOByBrand(brand);
        return new ResponseEntity<>(accessories, HttpStatus.OK);
    }

    @GetMapping("/price-range")
    public ResponseEntity<List<AccessoryDTO>> getAccessoriesByPriceRange(
            @RequestParam BigDecimal minPrice,
            @RequestParam BigDecimal maxPrice) {
        List<AccessoryDTO> accessories = accessoryService.findAccessoryDTOByPriceRange(minPrice, maxPrice);
        return new ResponseEntity<>(accessories, HttpStatus.OK);
    }

    @GetMapping("/popular")
    public ResponseEntity<List<AccessoryDTO>> getPopularAccessories() {
        List<AccessoryDTO> accessories = accessoryService.findAccessoryDTOByPopularity();
        return new ResponseEntity<>(accessories, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<AccessoryDTO>> searchAccessoriesByName(@RequestParam String name) {
        List<AccessoryDTO> accessories = accessoryService.searchAccessoryDTOByName(name);
        return new ResponseEntity<>(accessories, HttpStatus.OK);
    }
}
