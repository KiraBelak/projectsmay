package com.alldata.jproject.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.alldata.jproject.modelsDTO.MarcaDTO;
import com.alldata.jproject.services.MarcaService;

@RestController
@RequestMapping(path = "/api/marcas")
public class MarcaController {
    private final MarcaService marcaService;

    public MarcaController(MarcaService _marcaService) {
        this.marcaService = _marcaService;
    }

    @PostMapping
    public ResponseEntity<MarcaDTO> Post(@RequestBody MarcaDTO marca) {
        return ResponseEntity.ok(marcaService.Save(marca));
    }

    @GetMapping
    public ResponseEntity<List<MarcaDTO>> GetAll() {
        return ResponseEntity.ok(marcaService.Get());
    }

    @PutMapping("{id}")
    public ResponseEntity<MarcaDTO> Put(@RequestParam Integer id, @RequestBody MarcaDTO marca) {
        return ResponseEntity.ok(marcaService.Update(id, marca));
    }

    @GetMapping("{id}")
    public ResponseEntity<MarcaDTO> GetById(@PathVariable Integer id) {
        return ResponseEntity.ok(marcaService.GetById(id));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<Void> Delete(@PathVariable Integer id) {
        marcaService.Delete(id);
        return ResponseEntity.noContent().build();
    }
}
