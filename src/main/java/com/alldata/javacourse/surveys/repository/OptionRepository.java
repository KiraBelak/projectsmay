package com.alldata.javacourse.surveys.repository;

import com.alldata.javacourse.surveys.model.Option;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionRepository extends ListCrudRepository<Option, Integer> {
}
