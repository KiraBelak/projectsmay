package com.alldata.javacourse.surveys.repository;

import com.alldata.javacourse.surveys.model.Vote;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends ListCrudRepository<Vote, Vote.VotePk> {
    List<Vote> findByQuestionId(Integer questionId);
}
