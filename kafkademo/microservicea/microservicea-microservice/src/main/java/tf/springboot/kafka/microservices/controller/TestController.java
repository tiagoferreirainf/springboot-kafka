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

    private static int numberOfMessages = 100;

    @Autowired
    public TestController(KafkaSender kafkaSender){
        this.kafkaSender = kafkaSender;
    }

    @RequestMapping(value = "start", method = RequestMethod.POST)
    public void start() {
        log.info("Initiating sending kafka messages, a total of: {}", numberOfMessages);
        Faker faker = new Faker();
        int count = numberOfMessages;

        while (count > 0){
            final String msg = count + " - " + faker.animal().name();
            kafkaSender.sendMessage(msg);
            count--;
        }
    }
}
