package com.alldata.javacourse.surveys.service;

import com.alldata.javacourse.surveys.model.Option;
import com.alldata.javacourse.surveys.repository.OptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OptionService {
    private final OptionRepository optionRepository;

    @Autowired
    public OptionService(OptionRepository optionRepository) {
        this.optionRepository = optionRepository;
    }

    public List<Option> findAll() {
        return optionRepository.findAll();
    }

    public <S extends Option> S save(S entity) {
        return optionRepository.save(entity);
    }

    public Optional<Option> findById(Integer integer) {
        return optionRepository.findById(integer);
    }

    public void deleteById(Integer integer) {
        optionRepository.deleteById(integer);
    }
}
