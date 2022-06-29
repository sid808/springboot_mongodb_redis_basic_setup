package com.example.demo2;

import com.mongodb.MongoClientSettings;
import com.mongodb.connection.ConnectionPoolSettings;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfig {

  @Value("${app.name}")
  private String appName;

  @Value("${spring.data.mongodb.max-connections:200}")
  private int maxConnections;

  @Value("${spring.data.mongodb.min-connections:20}")
  private int minConnections;

  public MongoConfig() {
    System.out.println("In the constructor of MongoConfig");
  }

  @Bean
  public MongoClientSettings mongoClientSettings() {

    final MongoClientSettings clientSettings = MongoClientSettings.builder()
        .retryWrites(true)
        .applyToConnectionPoolSettings((ConnectionPoolSettings.Builder builder) -> {
          builder
              .maxSize(maxConnections) //connections count
              .minSize(minConnections)
              .maxConnectionLifeTime(0, TimeUnit.MILLISECONDS)
              .maxConnectionIdleTime(0, TimeUnit.MILLISECONDS)
              .maxWaitTime(5000, TimeUnit.MILLISECONDS)
          //.maxWaitQueueSize(5000)
          ;
        })
        .applyToSocketSettings(builder -> {
          builder.connectTimeout(2000, TimeUnit.MILLISECONDS);
        })
        .applicationName(appName)
        .build();

    System.out.println(" clientSettings:: " + clientSettings.toString());

    return clientSettings;
  }
}
