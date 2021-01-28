package producers;

import java.util.Random;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import config.KafkaConfig;

public class SimpleKafkaProducerWithCallBack {

    public static void main(final String[] args) {

	final Logger log = LoggerFactory.getLogger(SimpleKafkaProducerWithCallBack.class);

	final KafkaConfig kconfig = new KafkaConfig();
	final KafkaProducer<String, String> producer = new KafkaProducer<String, String>(kconfig.getProducerProps());
	for (int i = 0; i < 100; i++) {
	    createAndSendingMessage(log, kconfig, producer);
	}
	// Flush data
	producer.flush();
	// Flush and close
	producer.close();
    }

    private static void createAndSendingMessage(final Logger log, final KafkaConfig kconfig,
	    final KafkaProducer<String, String> producer) {

	final String message = "Sending some random value " + (new Random().nextInt());
	final ProducerRecord<String, String> record = new ProducerRecord<String, String>(kconfig.getTopic(), message);

	// Send data - asynchronous call
	producer.send(record, new Callback() {
	    public void onCompletion(final RecordMetadata metadata, final Exception exception) {
		if (exception == null) {
		    log.info("TOPIC: " + metadata.topic());
		    log.info("OFFSET: " + metadata.offset());
		    log.info("PARTITION: " + metadata.partition());
		    log.info("TIMESTAMP: " + metadata.timestamp());
		} else {
		    log.error("Error sending data", exception);
		}
	    }
	});
    }
}
