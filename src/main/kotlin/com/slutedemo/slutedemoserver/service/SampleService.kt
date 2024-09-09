package com.slutedemo.slutedemoserver.service

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class SampleService {

    private val logger = LoggerFactory.getLogger(SampleService::class.java)

    fun process(input: String): Mono<String> {
        logger.info("Received input: {}", input)
        return Mono.just(input.toUpperCase())
    }
}