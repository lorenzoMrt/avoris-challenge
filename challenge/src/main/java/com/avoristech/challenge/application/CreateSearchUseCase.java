package com.avoristech.challenge.application;

import com.avoristech.challenge.domain.EventBus;
import com.avoristech.challenge.domain.model.SearchDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CreateSearchUseCase {
    private static final Logger LOG = LoggerFactory.getLogger(CreateSearchUseCase.class);
    private final EventBus eventBus;

    public CreateSearchUseCase(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public void execute(SearchDto dto) {
        LOG.info("[CreateSearchUseCase.execute] sending event");
        eventBus.send(dto);
    }
}
