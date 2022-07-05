package com.microservice.demo.kafka.to.elastic.service.transformer;

import com.microservice.demo.elastic.model.index.impl.TwitterIndexModel;
import com.microservices.demo.kafka.avro.model.TwitterAvroModel;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AvroToElasticModelTransformer {
    public List<TwitterIndexModel> getElasticModel(List<TwitterAvroModel> avroModels){
        return avroModels.stream().map(
                avroModel -> TwitterIndexModel.builder()
                        .userId(avroModel.getUserId())
                        .id(String.valueOf(avroModel.getId()))
                        .text(avroModel.getText())
                        .createdAt(ZonedDateTime.ofInstant(Instant.ofEpochMilli(avroModel.getCreatedAt()), ZoneId.systemDefault()))
                        .build()
        ).collect(Collectors.toList());
    }
}
