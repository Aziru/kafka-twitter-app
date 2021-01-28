package producers;

import java.util.Random;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import config.KafkaConfig;

public class SimpleKafkaProducer {

    public static void main(final String[] args) {

	final KafkaConfig kconfig = new KafkaConfig();
	final KafkaProducer<String, String> producer = new KafkaProducer<String, String>(kconfig.getProducerProps());
	final ProducerRecord<String, String> record = new ProducerRecord<String, String>(kconfig.getTopic(),
		"Sending some random value " + (new Random().nextInt()));

	// Asynchronous call
	producer.send(record);
	// Flush data
	producer.flush();
	// Flush and close
	producer.close();
    }
}
