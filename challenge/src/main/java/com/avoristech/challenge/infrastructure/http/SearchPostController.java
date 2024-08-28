package com.avoristech.challenge.infrastructure.http;

import com.avoristech.challenge.application.CreateSearchUseCase;
import com.avoristech.challenge.domain.model.SearchMapper;
import com.avoristech.challenge.infrastructure.http.req.SearchReq;
import com.avoristech.challenge.infrastructure.http.res.SearchRes;
import com.fasterxml.uuid.Generators;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class SearchPostController {

    private final CreateSearchUseCase createSearchUseCase;

    public SearchPostController(CreateSearchUseCase createSearchUseCase) {
        this.createSearchUseCase = createSearchUseCase;
    }

    @PostMapping("/search")
    ResponseEntity<SearchRes> create(@RequestBody @Valid SearchReq searchReq) throws IllegalArgumentException {
        UUID searchId = Generators.timeBasedEpochGenerator().generate();
        createSearchUseCase.execute(SearchMapper.toDto(searchReq, searchId));
        return new ResponseEntity<>(new SearchRes(searchId), HttpStatus.CREATED);
    }
}
