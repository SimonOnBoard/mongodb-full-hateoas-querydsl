package ru.itis.kpfu.simononboard.mongodb.spring.application;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.itis.kpfu.simononboard.mongodb.driver.application.EnumCodecProvider;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@Configuration
public class SimpleMongoConfig {

    @Bean
    public MongoTemplate mongoTemplate() {
        CodecRegistry pojoCodecRegistry =
                fromRegistries(getDefaultCodecRegistry(),
                        CodecRegistries.fromRegistries(
                                CodecRegistries.fromProviders(new EnumCodecProvider())));
        MongoClientSettings settings = MongoClientSettings.builder()
                .codecRegistry(pojoCodecRegistry)
                .build();
        MongoClient mongoClient = MongoClients.create(settings);
        return new MongoTemplate(mongoClient, "shop");
    }
}