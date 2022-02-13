# springboot-kafka

## Project Structure
The current project has the following structure:
- __kafkacluster__ - docker composer with a Kafka and Zookeeper, for local tests.
- __kafkaprovider__ -  module with kafka provider, it provides simple configuration over kafka producer and consumer.
- __kafkademo/microservicea__ - module that will be used as the message sender.
- __kafkademo/microserviceb__ - module that will be used as the message consumer.

## Build Project
Run in the root folder:
````
mvn clean install
````

## Using the Kafka Provider
Add the producer dependency to the demo microservice

````
<dependency>
    <groupId>tf.springboot.kafka</groupId>
    <artifactId>provider</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
````

### Enable producer
To enable the producer from the Kafka Provider add the following annotation:
````
@SpringBootApplication
@EnableKafkaProviderProducer
public class SpringMainApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringMainApplication.class, args);
	}
}
````

### Enable consumer
To enable the consumer from the Kafka Provider add the following annotation:
````
@SpringBootApplication
@EnableKafkaProviderConsumer
public class SpringMainApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringMainApplication.class, args);
	}
}
````
### Configuring Kafka Provider
````
tf:
  springboot:
    kafka:
      bootstrapServer: "localhost:9093"
      topic: "topic-test"
      groupId: "group-test"
      producer:
        enabled: true (disabled by default)
      consumer:
        enabled: true (disabled by default)
````

## Testing in K8s
### Installing kafka in k8s

````
helm repo add bitnami https://charts.bitnami.com/bitnami
helm install kafka bitnami/kafka
```` 

Check output result of running above commands to fetch kafka service and ports.
Traditionally the k8s service for producer is _"kafka-headless:9092"_ and _"kafka:9092"_ for the consumer.


### Installing & configuring demo microservices

After compiling demo microsevices, install the two microservices in a k8s cluster:

````
helm install demo-producer microservicea.tgz --set envs.host=YOUR_KAFKA_HOST
```` 

````
helm install demo-consumer microserviceb.tgz --set envs.host=YOUR_KAFKA_HOST
```` 