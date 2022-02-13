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

    @RequestMapping(value = "start/topicA", method = RequestMethod.POST)
    public void startA() {
        log.info("Initiating sending kafka messages to topic A, a total of: {}", numberOfMessages);
        int count = numberOfMessages;

        while (count > 0){
            final String msg = count + " - " + faker.animal().name();
            kafkaSender.sendMessage(topicNameA, msg);
            count--;
        }
    }

    @RequestMapping(value = "start/topicB", method = RequestMethod.POST)
    public void startB() {
        log.info("Initiating sending kafka messages to topic B, a total of: {}", numberOfMessages);
        int count = numberOfMessages;

        while (count > 0){
            final String msg = count + " - " + faker.name().fullName();
            kafkaSender.sendMessage(topicNameB, msg);
            count--;
        }
    }

    @RequestMapping(value = "start/topicC", method = RequestMethod.POST)
    public void startC() {
        log.info("Initiating sending kafka messages to topic C, a total of: {}", numberOfMessages);
        int count = numberOfMessages;

        while (count > 0){
            final String msg = count + " - " + faker.address().fullAddress();
            kafkaSender.sendMessage(topicNameC, msg);
            count--;
        }
    }
}
