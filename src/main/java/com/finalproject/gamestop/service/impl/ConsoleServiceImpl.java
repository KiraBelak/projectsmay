package com.finalproject.gamestop.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finalproject.gamestop.dto.ConsoleDTO;
import com.finalproject.gamestop.dto.ReviewDTO;
import com.finalproject.gamestop.exception.ResourceNotFoundException;
import com.finalproject.gamestop.mapper.ProductMapper;
import com.finalproject.gamestop.model.Console;
import com.finalproject.gamestop.model.Review;
import com.finalproject.gamestop.repository.ConsoleRepository;
import com.finalproject.gamestop.service.ConsoleService;

@Service
public class ConsoleServiceImpl implements ConsoleService {

    @Autowired
    private ConsoleRepository consoleRepository;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<ConsoleDTO> getAllConsoleDTOs() {
        return consoleRepository.findAll().stream()
                .map(productMapper::consoleToConsoleDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ConsoleDTO getConsoleDTOById(Long id) {
        Console console = consoleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Console not found with id: " + id));
        return productMapper.consoleToConsoleDTO(console);
    }

    @Override
    public ConsoleDTO createConsole(ConsoleDTO consoleDTO) {
        Console console = productMapper.consoleDTOToConsole(consoleDTO);

        if (consoleDTO.getReviews() != null) {
            for (ReviewDTO reviewDTO : consoleDTO.getReviews()) {
                Review review = new Review();
                review.setUserName(reviewDTO.getUserName());
                review.setRating(reviewDTO.getRating());
                review.setComment(reviewDTO.getComment());
                review.setReviewDate(LocalDateTime.now());

                review.setProduct(console);
                console.getReviews().add(review);
            }
        }

        Console savedConsole = consoleRepository.save(console);
        return productMapper.consoleToConsoleDTO(savedConsole);
    }

    @Override
    public ConsoleDTO updateConsole(Long id, ConsoleDTO consoleDTO) {
        Console existingConsole = consoleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Console not found with id: " + id));

        if (consoleDTO.getName() != null)
            existingConsole.setName(consoleDTO.getName());
        if (consoleDTO.getDescription() != null)
            existingConsole.setDescription(consoleDTO.getDescription());
        if (consoleDTO.getPrice() != null)
            existingConsole.setPrice(consoleDTO.getPrice());
        if (consoleDTO.getStock() != null)
            existingConsole.setStock(consoleDTO.getStock());
        if (consoleDTO.getManufacturer() != null)
            existingConsole.setManufacturer(consoleDTO.getManufacturer());
        if (consoleDTO.getModel() != null)
            existingConsole.setModel(consoleDTO.getModel());
        if (consoleDTO.getReleaseYear() != null)
            existingConsole.setReleaseYear(consoleDTO.getReleaseYear());
        if (consoleDTO.getGeneration() != null)
            existingConsole.setGeneration(consoleDTO.getGeneration());

        Console updatedConsole = consoleRepository.save(existingConsole);
        return productMapper.consoleToConsoleDTO(updatedConsole);
    }

    @Override
    public void deleteConsole(Long id) {
        Console console = consoleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Console not found with id: " + id));
        consoleRepository.delete(console);
    }

    @Override
    public List<ConsoleDTO> findConsoleDTOByManufacturer(String manufacturer) {
        return consoleRepository.findByManufacturer(manufacturer).stream()
                .map(productMapper::consoleToConsoleDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ConsoleDTO> findConsoleDTOByGeneration(String generation) {
        return consoleRepository.findByGeneration(generation).stream()
                .map(productMapper::consoleToConsoleDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ConsoleDTO> findConsoleDTOByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return consoleRepository.findByPriceBetween(minPrice.doubleValue(), maxPrice.doubleValue()).stream()
                .map(productMapper::consoleToConsoleDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ConsoleDTO> findConsoleDTOByPopularity() {
        return consoleRepository.findAllByOrderByRatingDesc().stream()
                .filter(console -> console.getRating() >= 4.5)
                .map(productMapper::consoleToConsoleDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ConsoleDTO> searchConsoleDTOByName(String name) {
        return consoleRepository.findByNameContainingIgnoreCase(name).stream()
                .map(productMapper::consoleToConsoleDTO)
                .collect(Collectors.toList());
    }
}
