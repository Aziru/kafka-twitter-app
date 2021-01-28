package config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KafkaConfig {

    private final String topic = "test_topic";

    private final Map<String, Object> consumerProps;

    private final Map<String, Object> producerProps;

    private final Logger log = LoggerFactory.getLogger(KafkaConfig.class);

    public KafkaConfig() {
	// Initializing producer
	producerProps = new HashMap<String, Object>();
	producerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
	producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
	producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
	// Initializing consumer
	consumerProps = new HashMap<String, Object>();
	consumerProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
	consumerProps.put(ConsumerConfig.GROUP_ID_CONFIG, "my-first-consumer");
	consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
	consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
	// Extra configuration (earliest/latest/none)
	consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

	log.info("Kafka properties loaded...");
    }

    /**
     * Get producer configuration
     */
    public Map<String, Object> getProducerProps() {
	return producerProps;
    }

    /**
     * Get consumer configuration
     */
    public Map<String, Object> getConsumerProps() {
	return consumerProps;
    }

    /**
     * @return the topic
     */
    public String getTopic() {
	return topic;
    }
}
