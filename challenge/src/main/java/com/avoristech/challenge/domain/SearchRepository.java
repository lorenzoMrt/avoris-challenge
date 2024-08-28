package com.avoristech.challenge.domain;

import com.avoristech.challenge.domain.model.Search;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Meta;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface SearchRepository extends JpaRepository<Search, Integer> {
    Search findBySearchId(UUID searchUuid);

    @Meta(comment = "count searches with the same parameters")
    @Query("select count(s) from Search s where s.hotelId = ?1 and s.checkIn = ?2 and s.checkOut = ?3 and s.ages = ?4")
    long countSimilarSearches(String hotelId, LocalDate checkIn, LocalDate checkOut, List<Integer> ages);
}
