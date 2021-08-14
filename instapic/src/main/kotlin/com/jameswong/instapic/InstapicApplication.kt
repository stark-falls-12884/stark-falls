package com.jameswong.instapic

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class InstapicApplication

fun main(args: Array<String>) {
	runApplication<InstapicApplication>(*args)
}
