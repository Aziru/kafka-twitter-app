package consumers;

import java.time.Duration;
import java.util.Arrays;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import config.KafkaConfig;

public class SimpleKafkaConsumer {

    public static void main(final String[] args) {

	final Logger log = LoggerFactory.getLogger(SimpleKafkaConsumer.class);

	final KafkaConfig kconfig = new KafkaConfig();
	final KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(kconfig.getConsumerProps());

	// Subscribe topic
	consumer.subscribe(Arrays.asList(kconfig.getTopic()));

	// Poll data
	while (true) {
	    final ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofSeconds(5));
	    for (final ConsumerRecord<String, String> consumerRecord : consumerRecords) {
		log.info("Key: " + consumerRecord.key() + " - Value: " + consumerRecord.value());
		log.info("Partition: " + consumerRecord.partition() + " - Offset: " + consumerRecord.offset());
	    }
	}
    }

}
