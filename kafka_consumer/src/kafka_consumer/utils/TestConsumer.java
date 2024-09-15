package kafka_consumer.utils;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;


public class TestConsumer {
	public static void main(String[] args) {
		
	BasicConfigurator.configure();
	
	Properties props = new Properties();
	props.put("bootstrap.servers", "localhost:9092");
	props.put("group.id", "test-group");
	props.put("enable.auto.commit", "true");
	props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
	props.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
	
	KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
	//the consumer can subscribe to multiple topics; input as an arrayList
	consumer.subscribe(Arrays.asList("source-topic"));
	
	while(true) {
		ConsumerRecords<String, String> records = consumer.poll(5);
		for(ConsumerRecord<String, String> record : records) {
			System.out.printf("offset = %d, key = %s, value = %s\n",
			record.offset(),record.key(),record.value());
		}
		
	}
}
	
}
