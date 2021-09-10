package com.peysen.kafka.producer;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;

import static org.apache.kafka.clients.producer.ProducerConfig.*;

/**
 * @Auther: peimengmeng
 * @Date: 2021/8/8_09:25
 * @Desc:
 */
public class MyProducer {

    public static void main(String[] args) {

        Properties properties = new Properties();
        properties.setProperty(BOOTSTRAP_SERVERS_CONFIG, "kafka-broker:9092");
        properties.setProperty(KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        properties.setProperty(VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

        try (KafkaProducer kafkaProducer = new KafkaProducer<>(properties)) {
            for(int i=0; i<10;i++){
                ProducerRecord<String, String> producerRecord = new ProducerRecord<>("mykafka", "peysen"+i);
                kafkaProducer.send(producerRecord, new Callback() {
                    @Override
                    public void onCompletion(RecordMetadata metadata, Exception exception) {
                        System.out.println("---------------" + metadata.topic() + "," + metadata.partition());
                    }
                });
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
