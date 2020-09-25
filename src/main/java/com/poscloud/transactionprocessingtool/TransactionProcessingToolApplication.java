package com.poscloud.transactionprocessingtool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;
import java.util.TimeZone;
@EnableTransactionManagement

@EnableJpaRepositories(basePackages = "com.poscloud.transactionprocessingtool")
@EntityScan(basePackages = "com.poscloud.transactionprocessingtool.domain")
@EnableAsync
@EnableScheduling

@SpringBootApplication
public class TransactionProcessingToolApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransactionProcessingToolApplication.class, args);
    }

}
