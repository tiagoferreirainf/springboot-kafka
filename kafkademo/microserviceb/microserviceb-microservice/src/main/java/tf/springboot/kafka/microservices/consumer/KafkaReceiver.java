package tf.springboot.kafka.microservices.consumer;


import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaReceiver {

    @KafkaListener(topics = "test-topic", groupId = "group-id")
    public void listen(String message) {
       log.info("Received Messasge in group: " + message);
    }
}
