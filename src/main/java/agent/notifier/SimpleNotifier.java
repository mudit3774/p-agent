package agent.notifier;

import agent.detector.Alert;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

public class SimpleNotifier implements Notifier {

    private KafkaTemplate<String, Set<Alert>> kafkaTemplate;

    public SimpleNotifier() {
        this.kafkaTemplate = kafkaTemplate();
    }

    private KafkaTemplate<String, Set<Alert>> kafkaTemplate() {
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(config));
    }

    @Override
    public void notify(Set<Alert> alert) {
        System.out.println("Batch and Notifying in parallel : " + alert.toString());
        kafkaTemplate.send("quickstart-events", alert);
        System.out.println("Notified : " + alert.toString());
    }
}
