package com.avoristech.challenge.infrastructure.http;

import com.avoristech.challenge.application.RetrieveSearchUseCase;
import com.avoristech.challenge.domain.model.SearchCountDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class SearchCountController {

    private final RetrieveSearchUseCase retrieveSearchUseCase;

    public SearchCountController(RetrieveSearchUseCase retrieveSearchUseCase) {
        this.retrieveSearchUseCase = retrieveSearchUseCase;
    }

    @GetMapping("/count/{searchId}")
    ResponseEntity<SearchCountDto> retrieve(@PathVariable String searchId) {
        var searchUuid = UUID.fromString(searchId);
        var result = retrieveSearchUseCase.execute(searchUuid);
        return ResponseEntity.ok(result);
    }
}
