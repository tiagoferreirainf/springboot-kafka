package tf.springboot.kafka.microservices.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@KafkaListener(topics = "topic-test-b")
public class KafkaTopicBReceiver {

    @KafkaHandler
    void listenToA(String message){
        log.info("TOPIC B - Received Message: " + message);
    }

    @KafkaHandler(isDefault = true)
    void listenToA(Object message){
        log.info("TOPIC B - Received Message: " + message.toString());
    }
}
