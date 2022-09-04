package dev.usman.consumer.service;

import dev.usman.consumer.entity.Wikimedia;
import dev.usman.consumer.repository.WikimediaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class kafkaDatabaseConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(kafkaDatabaseConsumer.class);
    private final WikimediaRepository wikimediaRepository;

    public kafkaDatabaseConsumer(WikimediaRepository wikimediaRepository) {
        this.wikimediaRepository = wikimediaRepository;
    }

    @KafkaListener(
            topics = "wikimedia_recentchange",
            groupId = "myGroup"
    )
    public void consumer(String eventMessage) {
        LOGGER.info(String.format("message received -> %s", eventMessage));

        Wikimedia wikimedia = new Wikimedia();
        wikimedia.setWikiEventData(eventMessage);

        wikimediaRepository.save(wikimedia);
    }

}
