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

    private static int numberOfMessages = 100;

    @Autowired
    public TestController(KafkaSender kafkaSender){
        this.kafkaSender = kafkaSender;
    }

    @RequestMapping(value = "start/topicA", method = RequestMethod.POST)
    public void startA() {
        log.info("Initiating sending kafka messages to topic A, a total of: {}", numberOfMessages);
        Faker faker = new Faker();
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
        Faker faker = new Faker();
        int count = numberOfMessages;

        while (count > 0){
            final String msg = count + " - " + faker.name().fullName();
            kafkaSender.sendMessage(topicNameB, msg);
            count--;
        }
    }
}
