package com.slutedemo.slutedemoserver

import org.slf4j.LoggerFactory
import org.slf4j.MDC
import org.springframework.stereotype.Component
import org.springframework.web.server.WebFilter
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono

//@Component
//class TraceIdWebFilter : WebFilter {
//    private val logger = LoggerFactory.getLogger(TraceIdWebFilter::class.java)
//    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
////        val traceId = exchange.request.headers.getFirst("X-B3-TraceId") ?: "ssssss"
////        val spanId = exchange.request.headers.getFirst("X-B3-SpanId") ?: "sssssA"
//
//        val traceId = exchange.request.headers.getFirst("X-B3-TraceId") ?: "N/A"
//        val spanId = exchange.request.headers.getFirst("X-B3-SpanId") ?: "N/A"
//
////        MDC.put("traceId", traceId)
////        MDC.put("spanId", spanId)
//        logger.info("Handling0 /test endpoint. traceId={}, spanId={}")
//        return chain.filter(exchange)
//            .doFinally {
////                MDC.remove("traceId")
////                MDC.remove("spanId")
//            }
//    }
//}