package com.avoristech.challenge.infrastructure.kafka;

import com.avoristech.challenge.domain.EventBus;
import com.avoristech.challenge.domain.model.SearchDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class SearchProducer implements EventBus {
    private static final Logger LOG = LoggerFactory.getLogger(SearchProducer.class);

    private final KafkaTemplate<String, SearchDto> kafkaTemplate;

    @Value(value = "${search.topic.name}")
    private String searchTopicName;

    public SearchProducer(KafkaTemplate<String, SearchDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    public void send(SearchDto searchDto) {
        var future = kafkaTemplate.send(searchTopicName, searchDto);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                LOG.info("Sent message ['{}']", searchDto);
            } else {
                LOG.error("Unable to send message=['{}'] due to '{}'", searchDto, ex.getMessage());
            }
        });
    }
}

