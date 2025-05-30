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
import com.alldata.jproject.modelsDTO.InstrumentoDTO;
import com.alldata.jproject.services.InstrumentoService;

@RestController
@RequestMapping(path = "/api/instrumentos")
public class InstrumentoController {
    private final InstrumentoService instrumentoService;

    public InstrumentoController(InstrumentoService _instrumentoService) {
        this.instrumentoService = _instrumentoService;
    }

    @PostMapping
    public ResponseEntity<InstrumentoDTO> Post(@RequestBody InstrumentoDTO instrumentoDTO) {
        return ResponseEntity.ok(instrumentoService.Save(instrumentoDTO));
    }

    @GetMapping
    public ResponseEntity<List<InstrumentoDTO>> GetAll() {
        return ResponseEntity.ok(instrumentoService.Get());
    }

    @PutMapping("{id}")
    public ResponseEntity<InstrumentoDTO> Put(@RequestParam Integer id, @RequestBody InstrumentoDTO instrumentoDTO) {
        return ResponseEntity.ok(instrumentoService.Update(id, instrumentoDTO));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<Void> Delete(@PathVariable Integer id) {
        instrumentoService.Delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<InstrumentoDTO> GetById(@PathVariable Integer id) {
        return ResponseEntity.ok(instrumentoService.GetById(id));
    }
}
