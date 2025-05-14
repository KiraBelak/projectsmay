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

import com.alldata.jproject.database.models.Clasificacion;
import com.alldata.jproject.database.services.ClasificacionService;

@RestController
@RequestMapping(path = "clasificaciones")
public class ClasificacionController {
    private final ClasificacionService clasificacionService;

    public ClasificacionController(ClasificacionService _clasificacionService) {
        this.clasificacionService = _clasificacionService;
    }

    @PostMapping
    public ResponseEntity<Clasificacion> Post(@RequestBody Clasificacion clasificacion) {
        return ResponseEntity.ok(clasificacionService.Save(clasificacion));
    }

    @GetMapping
    public ResponseEntity<List<Clasificacion>> GetAll() {
        return ResponseEntity.ok(clasificacionService.Get());
    }

    @PutMapping("{id}")
    public ResponseEntity<Clasificacion> Put(@RequestParam Long id, @RequestBody Clasificacion clasificacion) {
        return clasificacionService.Update(id, clasificacion);
    }

    @GetMapping("{id}")
    public ResponseEntity<Clasificacion> GetById(@PathVariable Long id) {
        return clasificacionService.GetById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> Delete(Long id) {
        return clasificacionService.GetById(id)
                .map(user -> {
                    clasificacionService.Delete(id);
                    return ResponseEntity.noContent().<Void>build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
