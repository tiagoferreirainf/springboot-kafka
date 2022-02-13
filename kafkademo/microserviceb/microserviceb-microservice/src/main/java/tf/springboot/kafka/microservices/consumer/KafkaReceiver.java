package tf.springboot.kafka.microservices.consumer;


import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaReceiver {

    private static final String topicNameC = "topic-test-c";

    @KafkaListener(topics = topicNameC, groupId = "group-id")
    public void listenToA(String message) {
       log.info("TOPIC C - Received Message: " + message);
    }

}
