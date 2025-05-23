package com.finalproject.gamestop.service;

import java.math.BigDecimal;
import java.util.List;

import com.finalproject.gamestop.dto.AccessoryDTO;

public interface AccessoryService {

    List<AccessoryDTO> getAllAccessoryDTOs();

    AccessoryDTO getAccessoryDTOById(Long id);

    AccessoryDTO createAccessory(AccessoryDTO accessoryDTO);

    AccessoryDTO updateAccessory(Long id, AccessoryDTO accessoryDTO);

    void deleteAccessory(Long id);

    List<AccessoryDTO> findAccessoryDTOByType(String type);

    List<AccessoryDTO> findAccessoryDTOByCompatibility(String compatibility);

    List<AccessoryDTO> findAccessoryDTOByBrand(String brand);

    List<AccessoryDTO> findAccessoryDTOByPriceRange(BigDecimal minPrice, BigDecimal maxPrice);

    List<AccessoryDTO> findAccessoryDTOByPopularity();

    List<AccessoryDTO> searchAccessoryDTOByName(String name);
}
