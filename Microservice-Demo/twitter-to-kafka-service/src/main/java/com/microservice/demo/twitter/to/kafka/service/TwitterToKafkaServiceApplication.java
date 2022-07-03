package com.microservice.demo.twitter.to.kafka.service;

import com.microservice.demo.config.TwitterToKafkaServiceConfigData;
import com.microservice.demo.twitter.to.kafka.service.init.StreamInitializer;
import com.microservice.demo.twitter.to.kafka.service.runner.StreamRunner;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = "com.microservice.demo")
public class TwitterToKafkaServiceApplication implements CommandLineRunner {
    @Value("${spring.profiles.active}")
    private String activeProfile;

    private static final Logger LOG = LoggerFactory.getLogger(TwitterToKafkaServiceApplication.class);

    //Instead of Autowired Injection, use Constructor Injection
    private final StreamRunner streamRunner;
    private final StreamInitializer streamInitializer;

    public TwitterToKafkaServiceApplication(StreamRunner runner, TwitterToKafkaServiceConfigData configData, StreamInitializer initializer) {
        this.streamRunner = runner;
        this.streamInitializer = initializer;
    }

    public static void main(String[] args) {
        SpringApplication.run(TwitterToKafkaServiceApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
       LOG.info("App starts...");
        System.out.println("Active Profile: " + activeProfile);
       streamInitializer.init();
       streamRunner.start();
    }
}
