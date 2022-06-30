package com.microservice.demo.twitter.to.kafka.service.runner.impl;

import com.microservice.demo.config.TwitterToKafkaServiceConfigData;
import com.microservice.demo.twitter.to.kafka.service.listener.TwitterKafkaStatusListener;
import com.microservice.demo.twitter.to.kafka.service.runner.StreamRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;
import twitter4j.FilterQuery;
import twitter4j.TwitterException;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

import javax.annotation.PreDestroy;
import java.util.Arrays;

@Component
@ConditionalOnExpression("not ${twitter-to-kafka-service.enable-v2-tweets} && not ${twitter-to-kafka-service.enable-mock-tweets}")
public class TwitterKafkaStreamRunner implements StreamRunner {

    private final TwitterKafkaStatusListener twitterKafkaStatusListener;
    private final TwitterToKafkaServiceConfigData twitterToKafkaServiceConfigData;

    private static final Logger LOG = LoggerFactory.getLogger(TwitterKafkaStreamRunner.class);

    private TwitterStream twitterStream;

    public TwitterKafkaStreamRunner(TwitterKafkaStatusListener statusListener, TwitterToKafkaServiceConfigData configData) {
        this.twitterKafkaStatusListener = statusListener;
        this.twitterToKafkaServiceConfigData = configData;
    }

    @Override
    public void start() throws TwitterException {
        twitterStream = new TwitterStreamFactory().getInstance();
        twitterStream.addListener(twitterKafkaStatusListener);
        addFilter();

    }
    @PreDestroy
    public void shutdown(){
        if(twitterStream != null){
            LOG.info("Closing twitter Stream");
            twitterStream.shutdown();
        }
    }

    private void addFilter() {
        String[] keywords = twitterToKafkaServiceConfigData.getTwitterKeywords().toArray(new String[0]);
        FilterQuery filterQuery = new FilterQuery(keywords);
        twitterStream.filter(filterQuery);
        LOG.info("Starting filtering twitter stream for keywords {}", Arrays.toString(keywords));
    }
}
