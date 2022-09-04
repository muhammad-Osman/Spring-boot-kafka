package dev.usman.consumer.producer.handler;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.MessageEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;

public class WikimediaChangesHandler implements EventHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(WikimediaChangesHandler.class);
    private KafkaTemplate<String, String> kafkaTemplate;
    private String topicValue;

    public WikimediaChangesHandler(KafkaTemplate<String, String> kafkaTemplate,
                                   String topicValue) {
        this.kafkaTemplate = kafkaTemplate;
        this.topicValue = topicValue;
    }

    @Override
    public void onOpen() throws Exception {

    }

    @Override
    public void onClosed() throws Exception {

    }

    @Override
    public void onMessage(String s, MessageEvent messageEvent) throws Exception {
        LOGGER.info(String.format("event data -> %s", messageEvent.getData()));

        kafkaTemplate.send(topicValue, messageEvent.getData());

    }

    @Override
    public void onComment(String s) throws Exception {

    }

    @Override
    public void onError(Throwable throwable) {

    }
}
