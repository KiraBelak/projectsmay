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

import com.alldata.jproject.database.models.Instrumento;
import com.alldata.jproject.database.services.InstrumentoService;

@RestController
@RequestMapping(path = "instrumetos")
public class InstrumentoController {
    private final InstrumentoService instrumentoService;

    public InstrumentoController(InstrumentoService _instrumentoService) {
        this.instrumentoService = _instrumentoService;
    }

    @PostMapping
    public ResponseEntity<Instrumento> Post(@RequestBody Instrumento instrumento) {
        return ResponseEntity.ok(instrumentoService.Save(instrumento));
    }

    @GetMapping
    public ResponseEntity<List<Instrumento>> GetAll() {
        return ResponseEntity.ok(instrumentoService.Get());
    }

    @PutMapping("{id}")
    public ResponseEntity<Instrumento> Put(@RequestParam Integer id, @RequestBody Instrumento instrumento) {
        return instrumentoService.Update(id, instrumento);
    }

    @GetMapping("{id}")
    public ResponseEntity<Instrumento> GetById(@PathVariable Integer id) {
        return instrumentoService.GetById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> Delete(Integer id) {
        return instrumentoService.GetById(id)
                .map(user -> {
                    instrumentoService.Delete(id);
                    return ResponseEntity.noContent().<Void>build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
