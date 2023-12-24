package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author VNElinpe
 * @since 1.0
 **/
@SpringBootApplication
@EnableKafka
public class MainApplication implements ApplicationRunner {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        for (int i = 0; i < 1000; i++) {
            kafkaTemplate.send("topic-001", i + "");
            TimeUnit.SECONDS.sleep(1);
        }
    }

    @KafkaListener(topics = "topic-001", groupId = "a")
    public void func001(String data) {
        System.out.println("func1: " + data);
    }

    @KafkaListener(topics = "topic-001", groupId = "a")
    public void func002(String data) {
        System.out.println("func2: " + data);
    }

    @KafkaListener(topics = "topic-001", groupId = "a")
    public void func003(String data) {
        System.out.println("func3: " + data);
    }

    @KafkaListener(topics = "topic-001", groupId = "a")
    public void func004(String data) {
        System.out.println("func4: " + data);
    }
}
