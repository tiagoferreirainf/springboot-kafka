package tf.springboot.kafka.provider;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.annotation.EnableKafka;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import(KafkaConsumerConfiguration.class)
@EnableKafka
@Configuration
public @interface EnableKafkaProviderConsumer {}
