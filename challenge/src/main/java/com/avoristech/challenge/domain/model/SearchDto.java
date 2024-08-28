package com.avoristech.challenge.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
@JsonInclude(JsonInclude.Include.NON_NULL)
public record SearchDto(
        @JsonProperty("searchId") UUID searchId,
        @JsonProperty("hotelId") String hotelId,
        @JsonProperty("checkIn") LocalDate checkIn,
        @JsonProperty("checkOut") LocalDate checkOut,
        @JsonProperty("ages") List<Integer> ages) {
    public SearchDto(UUID searchId, String hotelId, LocalDate checkIn, LocalDate checkOut, List<Integer> ages) {
        this.searchId = searchId;
        this.hotelId = Objects.requireNonNull(hotelId);
        this.checkIn = Objects.requireNonNull(checkIn);
        this.checkOut = Objects.requireNonNull(checkOut);
        this.ages = List.copyOf(ages);
    }

    @Override
    public String toString() {
        return "{" +
                "\"searchId\":\"" + (searchId == null ? "" : searchId) + "\"" +
                ", \"hotelId\":\"" + (hotelId == null ? "" : hotelId) + "\"" +
                ", \"checkIn\":\"" + (checkIn == null ? "" : checkIn) + "\"" +
                ", \"checkOut\":\"" + (checkOut == null ? "" : checkOut) + "\"" +
                ", \"ages\":\"" + (ages == null ? "" : ages) + "\"" +
                "}";
    }
}
