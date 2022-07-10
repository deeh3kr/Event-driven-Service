To run this MicroService:

Open CMD:

->go to docker-compose folder:

docker-compose -f common.yml -f kafka_cluster.yml up

then next terminal:

docker-compose -f common.yml -f services.yml up

------------------------------------------------------
Check Created Topics:

docker run -it --network=host confluentinc/cp-kafkacat kafkacat -L -b localhost:19092

Check Consumed Messages:

docker run -it --network=host confluentinc/cp-kafkacat kafkacat -C -b localhost:19092 -t twitter-topic

-------------------------------------------------------------------------
So, to run project:
1. Run Kafka and Elastic Search Docker composes.
2. then run config server->twitterToKafka->KAfkaToElastic->ElasticService->WebClient
in IntelliJ only.