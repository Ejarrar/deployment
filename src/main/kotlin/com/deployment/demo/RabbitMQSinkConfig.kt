package com.deployment.demo

import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.function.Consumer

@Configuration
class RabbitMQSinkConfig {
    private val log = LoggerFactory.getLogger(RabbitMQSinkConfig::class.java)

    @Bean
    fun testQueue(): Consumer<String> = Consumer { message -> let { Thread.sleep(10_000) }.also { log.info(message) } }
}