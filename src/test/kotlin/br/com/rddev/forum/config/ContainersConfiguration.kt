package br.com.rddev.forum.config

import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.GenericContainer
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.junit.jupiter.Container

abstract class ContainersConfiguration {
    companion object {
        @Container
        private val mysqlContainer = MySQLContainer<Nothing>("mysql:8.0.31").apply {
            withDatabaseName("testedb")
            withUsername("rapha")
            withPassword("123456")
        }

        @Container
        private val redisContainer = GenericContainer<Nothing>("redis:latest").apply {
            withExposedPorts(6379)
        }

        @JvmStatic
        @DynamicPropertySource
        fun properties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.driverClassName", mysqlContainer::getDriverClassName)
            registry.add("spring.datasource.url", mysqlContainer::getJdbcUrl)
            registry.add("spring.datasource.password", mysqlContainer::getPassword)
            registry.add("spring.datasource.username", mysqlContainer::getUsername)
            registry.add("spring.redis.host", redisContainer::getContainerIpAddress)
            registry.add("spring.redis.port", redisContainer::getFirstMappedPort)
        }

    }
}