package com.zj.study.framework.kafka.hello;

import java.util.Properties;
import java.util.concurrent.Future;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import com.zj.study.framework.kafka.BusiConst;

public class ProducerAsyn {

	public static void main(String[] args) {
		Properties properties = new Properties();
		properties.put("bootstrap.servers", "10.10.10.5:9092");
		properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);
		try {
			System.out.println("主綫程:" + Thread.currentThread().getId());
			ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>(BusiConst.HELLO_TOPIC,
					"zj", "zhaojianc");
			producer.send(producerRecord, new Callback() {

				@Override
				public void onCompletion(RecordMetadata metadata, Exception exception) {
					System.out.println("Callback綫程:" + Thread.currentThread().getId());
					if (null != exception) {
						exception.printStackTrace();
					}

					if (null != metadata) {
						System.out.println("offset" + metadata.offset() + "-" + "partition:" + metadata.partition());
					}
				}
			});
			System.out.println("message send.");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			producer.close();
		}
	}

}
