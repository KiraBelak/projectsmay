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

import com.alldata.jproject.models.Clasificacion;
import com.alldata.jproject.modelsDTO.ClasificacionDTO;
import com.alldata.jproject.services.ClasificacionService;

@RestController
@RequestMapping(path = "/api/clasificaciones")
public class ClasificacionController {
    private final ClasificacionService clasificacionService;

    public ClasificacionController(ClasificacionService _clasificacionService) {
        this.clasificacionService = _clasificacionService;
    }

    @PostMapping
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<Clasificacion> Post(@RequestBody Clasificacion clasificacion) {
        return ResponseEntity.ok(clasificacionService.Save(clasificacion));
    }

    @GetMapping
    public ResponseEntity<List<ClasificacionDTO>> GetAll() {
        return ResponseEntity.ok(clasificacionService.Get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Clasificacion> Put(@RequestParam Integer id, @RequestBody Clasificacion clasificacion) {
        return clasificacionService.Update(id, clasificacion);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClasificacionDTO> GetById(@PathVariable Integer id) {
        return ResponseEntity.ok(clasificacionService.GetById(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<Void> Delete(@PathVariable Integer id) {
        clasificacionService.Delete(id);
        return ResponseEntity.noContent().build();
    }
}
