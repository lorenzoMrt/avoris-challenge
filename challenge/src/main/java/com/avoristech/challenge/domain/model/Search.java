package com.avoristech.challenge.domain.model;

import com.avoristech.challenge.domain.ListToJsonConverter;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "searches")
public class Search {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "searches_seq")
    @SequenceGenerator(name = "searches_seq", sequenceName = "searches_seq", allocationSize = 1)
    private Integer id;
    @Column
    private UUID searchId;
    @Column
    private String hotelId;
    @Column
    private LocalDate checkIn;
    @Column
    private LocalDate checkOut;
    @Column
    @Convert(converter = ListToJsonConverter.class)
    private List<Integer> ages;

    public Search() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UUID getSearchId() {
        return searchId;
    }

    public void setSearchId(UUID searchId) {
        this.searchId = searchId;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    public List<Integer> getAges() {
        return ages;
    }

    public void setAges(List<Integer> ages) {
        this.ages = ages;
    }
}
