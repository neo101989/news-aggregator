package com.test.newsaggregator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAutoConfiguration(exclude=HibernateJpaAutoConfiguration.class)
@SpringBootApplication
@EnableScheduling
public class NewsAggregatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsAggregatorApplication.class, args);
	}
}
