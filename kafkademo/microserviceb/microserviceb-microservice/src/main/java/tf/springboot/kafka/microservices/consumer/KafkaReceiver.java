package tf.springboot.kafka.microservices.consumer;


import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaReceiver {

    private static final String topicNameA = "topic-test-a";
    private static final String topicNameB = "topic-test-b";

    @KafkaListener(topics = topicNameA, groupId = "group-id")
    public void listenToA(String message) {
       log.info("TOPIC A - Received Message: " + message);
    }

    @KafkaListener(topics = topicNameB, groupId = "group-id")
    public void listenToB(String message) {
        log.info("TOPIC B -Received Message: " + message);
    }
}
