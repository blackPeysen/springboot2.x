package com.peysen.kafka.consumer;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.assertj.core.util.Lists;

import java.time.Duration;
import java.util.Map;
import java.util.Properties;

import static org.apache.kafka.clients.consumer.ConsumerConfig.*;

/**
 * @Auther: peimengmeng
 * @Date: 2021/8/8_09:25
 * @Desc:
 */
public class MyConsumer {

    public static void main(String[] args) {

        Properties properties = new Properties();
        properties.setProperty(BOOTSTRAP_SERVERS_CONFIG, "kafka-broker:9092");
        properties.setProperty(GROUP_ID_CONFIG, "peysen-c-1");
        properties.setProperty(KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        properties.setProperty(VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");

        try (KafkaConsumer kafkaConsumer = new KafkaConsumer<>(properties)) {
            kafkaConsumer.subscribe(Lists.newArrayList("mykafka"));

            while (true){
                ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(Duration.ofSeconds(1));

                for(ConsumerRecord consumerRecord : consumerRecords){
                    System.out.println("==============topic:" + consumerRecord.topic() + ", partition:" + consumerRecord.partition() + ",offset:" + consumerRecord.offset());
                    System.out.println(consumerRecord.value().toString());
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
