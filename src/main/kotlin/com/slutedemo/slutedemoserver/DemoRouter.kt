package com.slutedemo.slutedemoserver

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class DemoRouter {

    @Bean
    fun mainRouter(userHandler: DemoHandler) = coRouter {
        "server/".nest {
            GET("/test",userHandler::testEndpoint)//{req-> req.coHandler {  userHandler.testEndpoint(it)}}
            GET("/test1",userHandler::deactivateAccount)
        }
    }
}