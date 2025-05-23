package com.finalproject.gamestop.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finalproject.gamestop.dto.AccessoryDTO;
import com.finalproject.gamestop.exception.ResourceNotFoundException;
import com.finalproject.gamestop.mapper.ProductMapper;
import com.finalproject.gamestop.model.Accessory;
import com.finalproject.gamestop.repository.AccessoryRepository;
import com.finalproject.gamestop.service.AccessoryService;

@Service
public class AccessoryServiceImpl implements AccessoryService {

    @Autowired
    private AccessoryRepository accessoryRepository;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<AccessoryDTO> getAllAccessoryDTOs() {
        return accessoryRepository.findAll().stream()
                .map(productMapper::accessoryToAccessoryDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AccessoryDTO getAccessoryDTOById(Long id) {
        Accessory accessory = accessoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Accessory not found with id: " + id));
        return productMapper.accessoryToAccessoryDTO(accessory);
    }

    @Override
    public AccessoryDTO createAccessory(AccessoryDTO accessoryDTO) {
        Accessory accessory = productMapper.accessoryDTOToAccessory(accessoryDTO);
        Accessory savedAccessory = accessoryRepository.save(accessory);
        return productMapper.accessoryToAccessoryDTO(savedAccessory);
    }

    @Override
    public AccessoryDTO updateAccessory(Long id, AccessoryDTO accessoryDTO) {
        Accessory existingAccessory = accessoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Accessory not found with id: " + id));

        if (accessoryDTO.getName() != null)
            existingAccessory.setName(accessoryDTO.getName());
        if (accessoryDTO.getDescription() != null)
            existingAccessory.setDescription(accessoryDTO.getDescription());
        if (accessoryDTO.getPrice() != null)
            existingAccessory.setPrice(accessoryDTO.getPrice());
        if (accessoryDTO.getStock() != null)
            existingAccessory.setStock(accessoryDTO.getStock());
        if (accessoryDTO.getType() != null)
            existingAccessory.setType(accessoryDTO.getType());
        if (accessoryDTO.getCompatibility() != null)
            existingAccessory.setCompatibility(accessoryDTO.getCompatibility());
        if (accessoryDTO.getBrand() != null)
            existingAccessory.setBrand(accessoryDTO.getBrand());

        Accessory updatedAccessory = accessoryRepository.save(existingAccessory);
        return productMapper.accessoryToAccessoryDTO(updatedAccessory);
    }

    @Override
    public void deleteAccessory(Long id) {
        Accessory accessory = accessoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Accessory not found with id: " + id));
        accessoryRepository.delete(accessory);
    }

    @Override
    public List<AccessoryDTO> findAccessoryDTOByType(String type) {
        return accessoryRepository.findByType(type).stream()
                .map(productMapper::accessoryToAccessoryDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AccessoryDTO> findAccessoryDTOByCompatibility(String compatibility) {
        return accessoryRepository.findByCompatibility(compatibility).stream()
                .map(productMapper::accessoryToAccessoryDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AccessoryDTO> findAccessoryDTOByBrand(String brand) {
        return accessoryRepository.findByBrand(brand).stream()
                .map(productMapper::accessoryToAccessoryDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AccessoryDTO> findAccessoryDTOByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return accessoryRepository.findByPriceBetween(minPrice.doubleValue(), maxPrice.doubleValue()).stream()
                .map(productMapper::accessoryToAccessoryDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AccessoryDTO> findAccessoryDTOByPopularity() {
        return accessoryRepository.findAllByOrderByRatingDesc().stream()
                .filter(accessory -> accessory.getRating() >= 4.5)
                .map(productMapper::accessoryToAccessoryDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AccessoryDTO> searchAccessoryDTOByName(String name) {
        return accessoryRepository.findByNameContainingIgnoreCase(name).stream()
                .map(productMapper::accessoryToAccessoryDTO)
                .collect(Collectors.toList());
    }
}
