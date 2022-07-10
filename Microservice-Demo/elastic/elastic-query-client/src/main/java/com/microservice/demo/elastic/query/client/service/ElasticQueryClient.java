package com.microservice.demo.elastic.query.client.service;


import com.microservice.demo.elastic.model.index.IndexModel;
import reactor.core.publisher.Flux;

import java.util.List;

public interface ElasticQueryClient<T extends IndexModel> {

    T getIndexModelById(String id);

    List<T> getIndexModelByText(String text);

    Flux<T> getIndexModelByTextAsFlux(String text);

    List<T> getAllIndexModels();
}
