package com.alldata.javacourse.surveys.service;

import com.alldata.javacourse.surveys.model.Vote;
import com.alldata.javacourse.surveys.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoteService {
    private final VoteRepository voteRepository;

    @Autowired
    public VoteService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public List<Vote> findAll() {
        return voteRepository.findAll();
    }

    public Vote save(Vote entity) {
        return voteRepository.save(entity);
    }

    public Optional<Vote> findById(Vote.VotePk id) {
        return voteRepository.findById(id);
    }

    public void deleteById(Vote.VotePk id) {
        voteRepository.deleteById(id);
    }
}
