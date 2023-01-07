package br.com.rddev.forum.config

import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.GenericContainer
import org.testcontainers.junit.jupiter.Container

abstract class RedisContainerConfiguration {
    companion object {
        @Container
        private val redisContainer = GenericContainer<Nothing>("redis:latest").apply {
            withExposedPorts(6379)
        }

        @JvmStatic
        @DynamicPropertySource
        fun properties(registry: DynamicPropertyRegistry) {
            registry.add("spring.redis.host", RedisContainerConfiguration.redisContainer::getContainerIpAddress)
            registry.add("spring.redis.port", RedisContainerConfiguration.redisContainer::getFirstMappedPort)
        }

    }
}