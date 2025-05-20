package com.alldata.javacourse.surveys.repository;

import com.alldata.javacourse.surveys.model.Question;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends ListCrudRepository<Question, Integer> {
}
