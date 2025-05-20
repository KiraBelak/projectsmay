package com.alldata.javacourse.surveys.repository;

import com.alldata.javacourse.surveys.model.Survey;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SurveyRepository extends ListCrudRepository<Survey, String> {
    List<Survey> findByUserId(Integer userId);
    Optional<Survey> findByCode(String code);
}
