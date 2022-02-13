package tf.springboot.kafka.microservices.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.RoutingKafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaSender {

    @Autowired
    private RoutingKafkaTemplate kafkaTemplate;

    public void sendMessage(String topicName, String msg) {
        log.info("Sending message:  {} ", msg);
        kafkaTemplate.send(topicName, msg);
    }
}
