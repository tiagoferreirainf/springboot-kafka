package tf.springboot.kafka.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tf.springboot.kafka.provider.EnableKafkaProviderProducer;

@SpringBootApplication
@EnableKafkaProviderProducer
public class SpringMainApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringMainApplication.class, args);
	}
}
