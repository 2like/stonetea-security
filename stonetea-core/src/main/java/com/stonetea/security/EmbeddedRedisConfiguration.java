package com.stonetea.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/*
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.HeaderHttpSessionIdResolver;
import org.springframework.session.web.http.HttpSessionIdResolver;

import redis.clients.jedis.Protocol;
import redis.embedded.RedisServer;
*/
import javax.annotation.PreDestroy;
import java.io.IOException;

@Configuration
//@EnableRedisHttpSession
public class EmbeddedRedisConfiguration {

/*    private static RedisServer redisServer;

    @Bean
    public JedisConnectionFactory connectionFactory() throws IOException {

        redisServer = new RedisServer(Protocol.DEFAULT_PORT);
        redisServer.start();
        return new JedisConnectionFactory();
   }

    @Bean
    public HttpSessionIdResolver httpSessionIdResolver() {
        return HeaderHttpSessionIdResolver.xAuthToken();
    }

   @PreDestroy
    public void destroy() {
        redisServer.stop();
   }
*/
}