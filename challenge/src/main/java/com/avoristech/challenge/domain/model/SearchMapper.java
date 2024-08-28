package com.avoristech.challenge.domain.model;

import com.avoristech.challenge.infrastructure.http.req.SearchReq;

import java.util.UUID;

public class SearchMapper {

    private SearchMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static Search toEntity(SearchDto searchDto) {
        var search = new Search();
        search.setSearchId(searchDto.searchId());
        search.setHotelId(searchDto.hotelId());
        search.setCheckIn(searchDto.checkIn());
        search.setCheckOut(searchDto.checkOut());
        search.setAges(searchDto.ages());

        return search;
    }

    public static SearchDto toDto(SearchReq searchReq, UUID searchId) {
        return new SearchDto(searchId, searchReq.hotelId(), searchReq.checkIn(), searchReq.checkOut(), searchReq.ages());
    }
}
