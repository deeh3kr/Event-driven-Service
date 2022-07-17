package com.microservice.demo.reactive.elastic.query.web.client.service;

import com.microservice.demo.elastic.query.web.client.common.model.ElasticQueryWebClientRequestModel;
import com.microservice.demo.elastic.query.web.client.common.model.ElasticQueryWebClientResponseModel;
import reactor.core.publisher.Flux;

public interface ElasticQueryWebClient {

    Flux<ElasticQueryWebClientResponseModel> getDataByText(ElasticQueryWebClientRequestModel request);
}
