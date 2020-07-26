package com.zj.study.framework.kafka.hello;

import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import com.zj.study.framework.kafka.BusiConst;

import edu.emory.mathcs.backport.java.util.Collections;

public class Consumer {

	public static void main(String[] args) {
		Properties properties = new Properties();
		properties.put("bootstrap.servers", "10.10.10.5:9092");
		properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		properties.put("group.id", "test");
		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
		try {
			consumer.subscribe(Collections.singletonList(BusiConst.HELLO_TOPIC));
			while (true) {

				ConsumerRecords<String, String> records = consumer.poll(500);
				for (ConsumerRecord<String, String> record : records) {
					System.out.println(String.format("主题：%s，分区：%d，偏移量：%d，" + "key：%s，value：%s", record.topic(),
							record.partition(), record.offset(), record.key(), record.value()));
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			consumer.close();
		}
	}

}
