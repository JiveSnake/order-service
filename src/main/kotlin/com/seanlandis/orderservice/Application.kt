package com.seanlandis.orderservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.jms.annotation.EnableJms

@SpringBootApplication
@EnableJms
class Application

fun main(args: Array<String>) {
	runApplication<Application>(*args)
}
