package tf.springboot.kafka.microservices.controller;

import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tf.springboot.kafka.microservices.producer.KafkaSender;

@Slf4j
@RestController
@RequestMapping(value = "/test/")
public class TestController {

    private KafkaSender kafkaSender;

    private static final String topicNameA = "topic-test-a";
    private static final String topicNameB = "topic-test-b";
    private static final String topicNameC = "topic-test-c";

    private static int numberOfMessages = 100;

    private static Faker faker = new Faker();

    @Autowired
    public TestController(KafkaSender kafkaSender){
        this.kafkaSender = kafkaSender;
    }

    private void sendLoopOfMessagesToTopic(String topic, String message){
        int count = numberOfMessages;
        while (count > 0){
            kafkaSender.sendMessage(topic, message);
            count--;
        }
    }

    @RequestMapping(value = "start/topicA", method = RequestMethod.POST)
    public void startA() {
        log.info("Initiating sending kafka messages to topic A, a total of: {}", numberOfMessages);
        sendLoopOfMessagesToTopic(topicNameA, faker.animal().name());
    }

    @RequestMapping(value = "start/topicB", method = RequestMethod.POST)
    public void startB() {
        log.info("Initiating sending kafka messages to topic B, a total of: {}", numberOfMessages);
        sendLoopOfMessagesToTopic(topicNameB, faker.name().fullName());
    }

    @RequestMapping(value = "start/topicC", method = RequestMethod.POST)
    public void startC() {
        log.info("Initiating sending kafka messages to topic C, a total of: {}", numberOfMessages);
        sendLoopOfMessagesToTopic(topicNameC, faker.address().fullAddress());
    }
}
