package com.slutedemo.slutedemoserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Hooks

@SpringBootApplication
class SlutedemoApplication {
    @Bean
    fun webClient(): WebClient {
        return WebClient.builder().build()
    }
}

fun main(args: Array<String>) {
   // Hooks.enableContextLossTracking()
    Hooks.enableAutomaticContextPropagation()
    runApplication<SlutedemoApplication>(*args) {
      //  addInitializers(routes())
    }

}


