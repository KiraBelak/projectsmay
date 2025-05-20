package com.alldata.javacourse.surveys.controller;

import com.alldata.javacourse.surveys.model.Vote;
import com.alldata.javacourse.surveys.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/vote")
public class VoteController {
    private final VoteService voteService;

    @Autowired
    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @GetMapping
    public List<Vote> findAll() {
        return voteService.findAll();
    }

    @PostMapping
    public Vote save(@RequestBody Vote entity) {
        return voteService.save(entity);
    }

    @GetMapping("/{questionId}/{userId}")
    public Optional<Vote> findById(@PathVariable Integer questionId, @PathVariable Integer userId) {
        return voteService.findById(new Vote.VotePk(questionId, userId));
    }

    @DeleteMapping("/{questionId}/{userId}")
    public void deleteById(@PathVariable Integer questionId, @PathVariable Integer userId) {
        voteService.deleteById(new Vote.VotePk(questionId, userId));
    }
}
