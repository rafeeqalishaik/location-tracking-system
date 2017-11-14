package com.location.tracking.template;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

/**
 * The Class MongoConfig.
 */
/**
 * @author Rafeeq Ali Shaik
 *
 */
@Configuration
public class MongoConfig {

    /**
     * Mongo.
     *
     * @return the mongo
     * @throws Exception
     *             the exception
     */
    @Bean
    public Mongo mongo() throws Exception {
	return new MongoClient("localhost");
    }

    /**
     * Mongo template.
     *
     * @return the mongo template
     * @throws Exception
     *             the exception
     */
    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
	return new MongoTemplate(mongo(), "test");
    }

}
