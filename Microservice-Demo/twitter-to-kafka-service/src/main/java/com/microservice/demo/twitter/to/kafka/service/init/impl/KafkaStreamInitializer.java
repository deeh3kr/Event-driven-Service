package com.microservice.demo.twitter.to.kafka.service.init.impl;

import com.microservice.demo.config.KafkaConfigData;
import com.microservice.demo.kafka.admin.client.KafkaAdminClient;
import com.microservice.demo.twitter.to.kafka.service.init.StreamInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class KafkaStreamInitializer implements StreamInitializer {
    private static final Logger LOG = LoggerFactory.getLogger(KafkaStreamInitializer.class);

    private final KafkaConfigData kafkaConfigData;
    private final KafkaAdminClient kafkaAdminClient;

    public KafkaStreamInitializer(KafkaConfigData configData, KafkaAdminClient adminClient) {
        this.kafkaConfigData = configData;
        this.kafkaAdminClient = adminClient;
    }

    @Override
    public void init() {

        kafkaAdminClient.createTopic();
        kafkaAdminClient.checkSchemaRegistry();
        LOG.info("Topic with Name {} is ready for operation!", kafkaConfigData.getTopicNamesToCreate().toArray());

    }
}
