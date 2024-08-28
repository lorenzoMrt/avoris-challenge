package com.avoristech.challenge.domain;

import com.avoristech.challenge.domain.model.SearchDto;

public interface EventBus {

    void send(SearchDto searchDto);
}
