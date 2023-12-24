package org.example;

import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * TODO
 *
 * @author VNElinpe
 * @since 1.0
 **/
@SpringBootApplication
public class MongoApplication implements ApplicationRunner {
    public static void main(String[] args) {
        SpringApplication.run(MongoApplication.class, args);
    }

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    @Qualifier("redisTemplate4Cache")
    private RedisTemplate redisTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        try (MongoCursor<Document> iterator = mongoTemplate.getCollection("test001").find().batchSize(10000).iterator()) {
//            while (iterator.hasNext()) {
//                iterator.next();
//            }
//        }
//        try (MongoCursor<Document> iterator = mongoTemplate.getCollection("test002").find().batchSize(10000).iterator()) {
//            while (iterator.hasNext()) {
//                iterator.next();
//            }
//        }
//        Instant now = Instant.now();
//        for (int i = 0; i < 100; i++) {
//            List<Document> result = new ArrayList<>();
//            try (MongoCursor<Document> iterator = mongoTemplate.getCollection("test001").find().batchSize(10000).iterator()) {
//                while (iterator.hasNext()) {
//                    result.add(iterator.next());
//                }
//            }
//        }
//        System.out.println(Duration.between(now, Instant.now()));
//
//        now = Instant.now();
//        for (int i = 0; i < 100; i++) {
//            List<Document> result = new ArrayList<>();
//            try (MongoCursor<Document> iterator = mongoTemplate.getCollection("test002").find().batchSize(10000).iterator()) {
//                while (iterator.hasNext()) {
//                    result.add(iterator.next());
//                }
//            }
//        }
//        System.out.println(Duration.between(now, Instant.now()));
        System.out.println();


//        byte[] data1 = new byte[2500];
//        List<Document> dataBase64 = new ArrayList<>(1000);
//        List<Document> dataArray = new ArrayList<>(1000);
//        for (int i = 0; i < 1000; i++) {
//            Document document = new Document();
//            Document document1 = new Document();
//            for (int j = 0; j < 50; j++) {
//                ThreadLocalRandom.current().nextBytes(data1);
//                document.put("a" + j, data1);
//                document1.put("a" + j, Base64.getEncoder().encodeToString(data1));
//            }
//            dataBase64.add(document1);
//            dataArray.add(document);
//        }
//        mongoTemplate.insert(dataBase64, "test001");
//        mongoTemplate.insert(dataArray, "test002");
//        System.out.println();
    }
}
