package com.zj.study.framework.kafka.hello;

import java.util.Properties;
import java.util.concurrent.Future;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import com.zj.study.framework.kafka.BusiConst;

public class Producer {

	public static void main(String[] args) {
		Properties properties = new Properties();
		properties.put("bootstrap.servers", "10.10.10.5:9092");
		properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);
		try {
			ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>(BusiConst.HELLO_TOPIC,
					"zj", "zhaojianc");
			Future<RecordMetadata> future = producer.send(producerRecord);
			System.out.println("message send.");
			RecordMetadata recordMetadata = future.get();
			if (null != recordMetadata) {
				System.out
						.println("offset" + recordMetadata.offset() + "-" + "partition:" + recordMetadata.partition());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			producer.close();
		}
	}

}
