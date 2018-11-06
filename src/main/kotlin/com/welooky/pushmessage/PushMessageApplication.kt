package com.welooky.pushmessage

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@SpringBootApplication
@EnableWebMvc
class PushMessageApplication

fun main(args: Array<String>) {
    runApplication<PushMessageApplication>(*args)
}
