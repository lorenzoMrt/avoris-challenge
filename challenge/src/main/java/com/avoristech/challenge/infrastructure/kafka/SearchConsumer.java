package com.avoristech.challenge.infrastructure.kafka;

import com.avoristech.challenge.domain.SearchRepository;
import com.avoristech.challenge.domain.model.SearchDto;
import com.avoristech.challenge.domain.model.SearchMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class SearchConsumer {
    private static final Logger LOG = LoggerFactory.getLogger(SearchConsumer.class);
    private final SearchRepository repository;

    public SearchConsumer(SearchRepository repository) {
        this.repository = repository;
    }

    @KafkaListener(id = "searchListener",topics = "${search.topic.name}", groupId = "${spring.kafka.consumer.group-id}", containerFactory = "searchKafkaListenerContainerFactory")
    public void consume(SearchDto searchDto) {
        LOG.info("Received search event: '{}'", searchDto);
        var search = SearchMapper.toEntity(searchDto);
        repository.save(search);
    }
}

