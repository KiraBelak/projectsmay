package com.finalproject.gamestop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.finalproject.gamestop.dto.AccessoryDTO;
import com.finalproject.gamestop.dto.ConsoleDTO;
import com.finalproject.gamestop.dto.ProductDTO;
import com.finalproject.gamestop.dto.VideogameDTO;
import com.finalproject.gamestop.model.Accessory;
import com.finalproject.gamestop.model.Console;
import com.finalproject.gamestop.model.Product;
import com.finalproject.gamestop.model.Videogame;

@Mapper(componentModel = "spring", uses = { ReviewMapper.class })
public interface ProductMapper {

    // Default mapping for Product to ProductDTO
    @Mapping(source = "reviews", target = "reviews")
    @Mapping(target = "accessoryType", ignore = true)
    @Mapping(target = "brand", ignore = true)
    @Mapping(target = "compatibility", ignore = true)
    @Mapping(target = "developer", ignore = true)
    @Mapping(target = "generation", ignore = true)
    @Mapping(target = "genre", ignore = true)
    @Mapping(target = "manufacturer", ignore = true)
    @Mapping(target = "model", ignore = true)
    @Mapping(target = "platform", ignore = true)
    @Mapping(target = "publisher", ignore = true)
    @Mapping(target = "releaseYear", ignore = true)
    @Mapping(target = "type", ignore = true)
    ProductDTO toDTO(Product product);

    // Specific mapping for Videogame
    @Mapping(target = "type", constant = "videogame")
    @Mapping(target = "accessoryType", ignore = true)
    @Mapping(target = "compatibility", ignore = true)
    @Mapping(target = "brand", ignore = true)
    @Mapping(target = "manufacturer", ignore = true)
    @Mapping(target = "model", ignore = true)
    @Mapping(target = "generation", ignore = true)
    @Mapping(source = "reviews", target = "reviews")
    ProductDTO videogameToDTO(Videogame videogame);

    // Specific mapping for Console
    @Mapping(target = "type", constant = "console")
    @Mapping(target = "genre", ignore = true)
    @Mapping(target = "developer", ignore = true)
    @Mapping(target = "publisher", ignore = true)
    @Mapping(target = "platform", ignore = true)
    @Mapping(target = "accessoryType", ignore = true)
    @Mapping(target = "compatibility", ignore = true)
    @Mapping(target = "brand", ignore = true)
    @Mapping(source = "reviews", target = "reviews")
    ProductDTO consoleToDTO(Console console);

    // Specific mapping for Accessory
    @Mapping(target = "type", constant = "accessory")
    @Mapping(source = "type", target = "accessoryType")
    @Mapping(target = "genre", ignore = true)
    @Mapping(target = "developer", ignore = true)
    @Mapping(target = "publisher", ignore = true)
    @Mapping(target = "platform", ignore = true)
    @Mapping(target = "manufacturer", ignore = true)
    @Mapping(target = "model", ignore = true)
    @Mapping(target = "generation", ignore = true)
    @Mapping(target = "releaseYear", ignore = true)
    @Mapping(source = "reviews", target = "reviews")
    ProductDTO accessoryToDTO(Accessory accessory);

    // Convert DTO to Videogame
    @Mapping(target = "id", ignore = true)
    Videogame dtoToVideogame(ProductDTO dto);

    // Convert DTO to Console
    @Mapping(target = "id", ignore = true)
    Console dtoToConsole(ProductDTO dto);

    // Convert DTO to Accessory
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "accessoryType", target = "type")
    Accessory dtoToAccessory(ProductDTO dto);

    // Accessory
    @Mapping(source = "reviews", target = "reviews")
    AccessoryDTO accessoryToAccessoryDTO(Accessory accessory);

    Accessory accessoryDTOToAccessory(AccessoryDTO dto);

    // Console
    @Mapping(source = "reviews", target = "reviews")
    ConsoleDTO consoleToConsoleDTO(Console console);

    @Mapping(target = "reviews", ignore = true)
    Console consoleDTOToConsole(ConsoleDTO dto);

    // Videogame
    @Mapping(source = "reviews", target = "reviews")
    VideogameDTO videogameToVideogameDTO(Videogame videogame);

    Videogame videogameDTOToVideogame(VideogameDTO dto);
}
