package com.microservice.demo.kafka.to.elastic.service.consumer.impl;

import com.microservice.demo.config.KafkaConfigData;
import com.microservice.demo.config.KafkaConsumerConfigData;
import com.microservice.demo.elastic.index.client.service.ElasticIndexClient;
import com.microservice.demo.elastic.model.index.impl.TwitterIndexModel;
import com.microservice.demo.kafka.admin.client.KafkaAdminClient;
import com.microservice.demo.kafka.to.elastic.service.consumer.KafkaConsumer;
import com.microservice.demo.kafka.to.elastic.service.transformer.AvroToElasticModelTransformer;
import com.microservices.demo.kafka.avro.model.TwitterAvroModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class MockTweetsKafkaConsumer implements KafkaConsumer<Long, TwitterAvroModel> {

    private final Logger LOG = LoggerFactory.getLogger(MockTweetsKafkaConsumer.class);
    private final KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;
    private final KafkaAdminClient kafkaAdminClient;
    private final KafkaConfigData kafkaConfigData;
    private final AvroToElasticModelTransformer avroToElasticModelTransformer;
    private final ElasticIndexClient<TwitterIndexModel> elasticIndexClient;
    private final KafkaConsumerConfigData kafkaConsumerConfigData;

    public MockTweetsKafkaConsumer(KafkaListenerEndpointRegistry endpointRegistry,
                                   KafkaAdminClient adminClient,
                                   KafkaConfigData configData,
                                   AvroToElasticModelTransformer modelTransformer,
                                   ElasticIndexClient<TwitterIndexModel> indexClient,
                                   KafkaConsumerConfigData consumerConfigData) {
        this.kafkaListenerEndpointRegistry = endpointRegistry;
        this.kafkaAdminClient = adminClient;
        this.kafkaConfigData = configData;
        this.avroToElasticModelTransformer = modelTransformer;
        this.elasticIndexClient = indexClient;
        this.kafkaConsumerConfigData = consumerConfigData;
    }

    @EventListener
    public void onAppStartUp(ApplicationStartedEvent event){
        kafkaAdminClient.checkTopicsCreated();
        LOG.info("Topics with name {} is ready for Operation!", kafkaConfigData.getTopicNamesToCreate().toArray());
        Objects.requireNonNull(
                kafkaListenerEndpointRegistry.getListenerContainer(kafkaConsumerConfigData.getConsumerGroupId())).start();
    }

    @Override
    @KafkaListener(id = "${kafka-consumer-config.consumer-group-id}", topics = "${kafka-config.topic-name}")
    public void receive(@Payload List<TwitterAvroModel> messages,
                        @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) List<Integer> keys,
                        @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions,
                        @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
        LOG.info("{} number of messages received with keys {}\npartitions: {}\n And Offsets: {}, " + "sending " +
                "it to Elastic: Thread id {}", messages.size(), keys.toString(), partitions.toString(),
                offsets.toString(),
                Thread.currentThread().getId());
        List<TwitterIndexModel> indexModels = avroToElasticModelTransformer.getElasticModel(
                messages);
        List<String> ids = elasticIndexClient.save(indexModels);
        LOG.info("Documents saved to ElasticSearch with ids: {}", ids.toArray());

    }
}
