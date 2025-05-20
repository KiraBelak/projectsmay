package com.alldata.javacourse.surveys.controller;

import com.alldata.javacourse.surveys.model.Option;
import com.alldata.javacourse.surveys.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/option")
public class OptionController {
    private final OptionService optionService;

    @Autowired
    public OptionController(OptionService optionService) {
        this.optionService = optionService;
    }

    @GetMapping
    public List<Option> findAll() {
        return optionService.findAll();
    }

    @PostMapping
    public Option save(@RequestBody Option entity) {
        return optionService.save(entity);
    }

    @GetMapping("/{id}")
    public Optional<Option> findById(@PathVariable Integer id) {
        return optionService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        optionService.deleteById(id);
    }
}
