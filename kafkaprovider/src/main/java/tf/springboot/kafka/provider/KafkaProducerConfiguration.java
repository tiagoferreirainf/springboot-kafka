package tf.springboot.kafka.provider;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.core.RoutingKafkaTemplate;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Configuration
@ConditionalOnProperty(name = "tf.springboot.kafka.producer.enabled", havingValue = "true", matchIfMissing = false)
public class KafkaProducerConfiguration {

    @Value("${tf.springboot.kafka.bootstrapServer}")
    private String bootstratServer;

    @Value("${tf.springboot.kafka.topicPattern}")
    private String topicPattern;

    private Map<String, Object> getKafkaConfigurationProperties(){
        String server = Strings.isBlank(bootstratServer) ? "localhost:9093" : bootstratServer;

        Map<String, Object> kafkaConfigs = new HashMap<>();
        kafkaConfigs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, server);
        kafkaConfigs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        kafkaConfigs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        return kafkaConfigs;
    }

    @Bean
    public ProducerFactory<String, String> producerFactory() {
       return new DefaultKafkaProducerFactory<>(getKafkaConfigurationProperties());
    }

    @Bean
    public RoutingKafkaTemplate routingKafkaTemplate(GenericApplicationContext context) {

        Map<String, Object> kafkaProps = getKafkaConfigurationProperties();
        String pattern = Strings.isBlank(topicPattern) ? "topic-.*" : topicPattern;

        Map<Pattern, ProducerFactory<Object, Object>> map = new LinkedHashMap<>();
        map.put(Pattern.compile(pattern), new DefaultKafkaProducerFactory<>(kafkaProps));
        return new RoutingKafkaTemplate(map);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
