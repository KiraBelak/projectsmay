package com.alldata.jproject.database.controllers;

import java.util.List;

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

import com.alldata.jproject.database.models.Marca;
import com.alldata.jproject.database.services.MarcaService;

@RestController
@RequestMapping(path = "marcas")
public class MarcaController {
    private final MarcaService marcaService;

    public MarcaController(MarcaService _marcaService) {
        this.marcaService = _marcaService;
    }

    @PostMapping
    public ResponseEntity<Marca> Post(@RequestBody Marca marca) {
        return ResponseEntity.ok(marcaService.Save(marca));
    }

    @GetMapping
    public ResponseEntity<List<Marca>> GetAll() {
        return ResponseEntity.ok(marcaService.Get());
    }

    @PutMapping("{id}")
    public ResponseEntity<Marca> Put(@RequestParam Integer id, @RequestBody Marca marca) {
        return marcaService.Update(id, marca);
    }

    @GetMapping("{id}")
    public ResponseEntity<Marca> GetById(@PathVariable Integer id) {
        return marcaService.GetById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> Delete(Integer id) {
        return marcaService.GetById(id)
                .map(user -> {
                    marcaService.Delete(id);
                    return ResponseEntity.noContent().<Void>build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
