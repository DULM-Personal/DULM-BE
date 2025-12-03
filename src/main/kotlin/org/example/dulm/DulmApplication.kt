package org.example.dulm

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableCaching
@ConfigurationPropertiesScan
@EnableScheduling
class DulmApplication

fun main(args: Array<String>) {
    runApplication<DulmApplication>(*args)
}
