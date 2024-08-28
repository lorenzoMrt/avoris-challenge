package com.avoristech.challenge.application;

import com.avoristech.challenge.domain.SearchRepository;
import com.avoristech.challenge.domain.model.SearchCountDto;
import com.avoristech.challenge.domain.model.SearchDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RetrieveSearchUseCase {
    private final SearchRepository searchRepository;

    public RetrieveSearchUseCase(SearchRepository searchRepository) {
        this.searchRepository = searchRepository;
    }

    public SearchCountDto execute(UUID searchUuid) {
        var search = searchRepository.findBySearchId(searchUuid);
        var count = searchRepository.countSimilarSearches(search.getHotelId(), search.getCheckIn(), search.getCheckOut(), search.getAges());
        return new SearchCountDto(searchUuid, new SearchDto(null, search.getHotelId(), search.getCheckIn(), search.getCheckOut(), search.getAges()), count);
    }
}
