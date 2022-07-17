package com.microservice.demo.reactive.elastic.query.service.api;

import com.microservice.demo.elastic.model.index.impl.TwitterIndexModel;
import com.microservice.demo.elastic.query.service.common.model.ElasticQueryServiceRequestModel;
import com.microservice.demo.elastic.query.service.common.model.ElasticQueryServiceResponseModel;
import com.microservice.demo.reactive.elastic.query.service.business.ElasticQueryService;
import com.microservice.demo.reactive.elastic.query.service.repository.ElasticQueryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/documents")
public class ElasticDocumentController {

    private static final Logger LOG = LoggerFactory.getLogger(ElasticDocumentController.class);

    private final ElasticQueryRepository elasticQueryRepository;
    private final ElasticQueryService elasticQueryService;

    public ElasticDocumentController(ElasticQueryRepository repository, ElasticQueryService queryService) {
        this.elasticQueryRepository = repository;
        this.elasticQueryService = queryService;
    }

    @PostMapping(value = "/get-doc-by-text",
            produces = MediaType.TEXT_EVENT_STREAM_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Flux<ElasticQueryServiceResponseModel> getDocumentByText(
            @RequestBody @Valid ElasticQueryServiceRequestModel requestModel) {
        Flux<ElasticQueryServiceResponseModel> response =
                elasticQueryService.getDocumentByText(requestModel.getText());
        response = response.log();
        LOG.info("Returning from query reactive service for text {}!", requestModel.getText());
        return response;
    }

    @PostMapping("")
    public Mono<TwitterIndexModel> add(@RequestBody TwitterIndexModel twitterIndexModel){
        LOG.info("Saving Tweet into index!");
        return elasticQueryRepository.save(twitterIndexModel);
    }
}
