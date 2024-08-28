package com.avoristech.challenge.domain.model;

import java.util.UUID;

public record SearchCountDto(UUID searchId, SearchDto search, long count) {
}
