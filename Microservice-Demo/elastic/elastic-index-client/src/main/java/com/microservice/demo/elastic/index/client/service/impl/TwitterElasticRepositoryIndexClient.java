package com.microservice.demo.elastic.index.client.service.impl;

import com.microservice.demo.elastic.index.client.repository.TwitterElasticsearchIndexRepository;
import com.microservice.demo.elastic.index.client.service.ElasticIndexClient;
import com.microservice.demo.elastic.model.index.impl.TwitterIndexModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@ConditionalOnProperty(name = "elastic-config.is-repository", havingValue = "true", matchIfMissing = true)
public class TwitterElasticRepositoryIndexClient implements ElasticIndexClient<TwitterIndexModel> {
    private final Logger LOG = LoggerFactory.getLogger(TwitterElasticRepositoryIndexClient.class);

    private final TwitterElasticsearchIndexRepository twitterElasticsearchIndexRepository;

    public TwitterElasticRepositoryIndexClient(TwitterElasticsearchIndexRepository repository) {
        this.twitterElasticsearchIndexRepository = repository;
    }

    @Override
    public List<String> save(List documents) {
        List<TwitterIndexModel> indexModels = (List<TwitterIndexModel>)twitterElasticsearchIndexRepository.saveAll(documents);
        List<String> documentIds = indexModels.stream().map(
                TwitterIndexModel::getId).collect(Collectors.toList());
        LOG.info("Documents indexed successfully with type: {}, and ids: {}", TwitterIndexModel.class.getName(),
                documentIds);
        return documentIds;
    }
}
