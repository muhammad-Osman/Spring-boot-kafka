package dev.usman.consumer.producer.service;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import dev.usman.consumer.producer.handler.WikimediaChangesHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Service
public class WikimediaChangesProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(WikimediaChangesProducer.class);

    private KafkaTemplate<String, String> kafkaTemplate;

    public WikimediaChangesProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage() {
        try {
            String topic = "wikimedia_recentchange";

            EventHandler eventHandler = new WikimediaChangesHandler(kafkaTemplate, topic);

            String eventSourceUrl = "https://stream.wikimedia.org/v2/stream/recentchange";

            EventSource.Builder builder = new EventSource.Builder(eventHandler,
                    URI.create(eventSourceUrl));

            EventSource eventSource = builder.build();

            eventSource.start();

            TimeUnit.MINUTES.sleep(10);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

}
