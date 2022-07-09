package com.microservice.demo.elastic.query.web.client.service;


import com.microservice.demo.elastic.query.web.client.model.ElasticQueryWebClientRequestModel;
import com.microservice.demo.elastic.query.web.client.model.ElasticQueryWebClientResponseModel;

import java.util.List;

public interface ElasticQueryWebClient {

    List<ElasticQueryWebClientResponseModel> getDataByText(ElasticQueryWebClientRequestModel requestModel);
}
