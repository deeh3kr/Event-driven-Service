package com.microservice.demo.elastic.query.service.transformer;

import com.microservice.demo.elastic.model.index.impl.TwitterIndexModel;
import com.microservice.demo.elastic.query.service.model.ElasticQueryServiceResponseModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ElasticToResponseModelTransformer {
    public ElasticQueryServiceResponseModel getResponseModel(TwitterIndexModel twitterIndexModel){
        return ElasticQueryServiceResponseModel.builder()
                .id(twitterIndexModel.getId())
                .text(twitterIndexModel.getText())
                .userId(twitterIndexModel.getUserId())
                .createdAt(twitterIndexModel.getCreatedAt())
                .build();
    }

    public List<ElasticQueryServiceResponseModel> getAllResponseModel(List<TwitterIndexModel> indexModels){
        return indexModels.stream().map(this::getResponseModel).collect(Collectors.toList());
    }
}
