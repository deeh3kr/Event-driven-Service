package com.microservice.demo.twitter.to.kafka.service.transformer;

import com.microservices.demo.kafka.avro.model.TwitterAvroModel;
import jdk.jshell.Snippet;
import org.springframework.stereotype.Component;
import twitter4j.Status;

@Component
public class TwitterStatusToAvroTransformer {
    public TwitterAvroModel getTwitterAvroModelFromFromStatus(Status status){
        return TwitterAvroModel.newBuilder()
                .setId(status.getId())
                .setCreatedAt(status.getCreatedAt().getTime())
                .setUserId(status.getUser().getId())
                .setText(status.getText())
                .build();
    }
}
