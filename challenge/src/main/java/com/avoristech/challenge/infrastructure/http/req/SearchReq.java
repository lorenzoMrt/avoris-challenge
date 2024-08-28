package com.avoristech.challenge.infrastructure.http.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;

public record SearchReq(
        @Size(min = 5, max = 20, message = "Hotel ID must be between 5 and 20 characters long")
        @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).+$", message = "Hotel ID must contain at least one uppercase letter, one lowercase letter, and one digit")
        String hotelId,
        @NotNull(message = "Check-in date cannot be null")
        @FutureOrPresent(message = "Check-in date must be today or in the future")
        @JsonFormat(pattern="dd-MM-yyyy")
        LocalDate checkIn,
        @NotNull(message = "Check-out date cannot be null")
        @Future(message = "Check-out date must be in the future")
        @JsonFormat(pattern="dd-MM-yyyy")
        LocalDate checkOut,
        @NotEmpty(message = "Ages list cannot be empty")
        @Size(max = 10, message = "Ages list cannot have more than 10 entries")
        List<@NotNull @Min(value = 0, message = "Age cannot be negative") @Max(value = 120, message = "Age cannot be greater than 120") Integer> ages
) {
    public SearchReq {
        if (checkIn != null && checkOut != null && !checkOut.isAfter(checkIn)) {
            throw new IllegalArgumentException("Check-out date must be after check-in date");
        }
    }
    @Override
    public String toString() {
        return "{" +
                "\"hotelId\": \"" + (hotelId != null ? hotelId : "") + "\"," +
                "\"checkIn\": \"" + (checkIn != null ? checkIn : "") + "\"," +
                "\"checkOut\": \"" + (checkOut != null ? checkOut : "") + "\"," +
                "\"ages\": " + (ages != null ? ages.toString() : "{}") +
                "}";
    }
}
