package com.finalproject.gamestop.service;

import java.math.BigDecimal;
import java.util.List;

import com.finalproject.gamestop.dto.ConsoleDTO;

public interface ConsoleService {

    List<ConsoleDTO> getAllConsoleDTOs();

    ConsoleDTO getConsoleDTOById(Long id);

    ConsoleDTO createConsole(ConsoleDTO consoleDTO);

    ConsoleDTO updateConsole(Long id, ConsoleDTO consoleDTO);

    void deleteConsole(Long id);

    List<ConsoleDTO> findConsoleDTOByManufacturer(String manufacturer);

    List<ConsoleDTO> findConsoleDTOByGeneration(String generation);

    List<ConsoleDTO> findConsoleDTOByPriceRange(BigDecimal minPrice, BigDecimal maxPrice);

    List<ConsoleDTO> findConsoleDTOByPopularity();

    List<ConsoleDTO> searchConsoleDTOByName(String name);
}
