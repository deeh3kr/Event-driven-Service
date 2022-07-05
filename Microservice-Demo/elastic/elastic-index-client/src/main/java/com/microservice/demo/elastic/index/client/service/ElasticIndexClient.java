package com.microservice.demo.elastic.index.client.service;

import com.microservice.demo.elastic.model.index.IndexModel;

import java.util.List;

public interface ElasticIndexClient<T extends IndexModel> {
    List<String> save(List<T> documents);
}