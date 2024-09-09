package com.slutedemo.slutedemoserver.util

import io.micrometer.tracing.Span
import io.micrometer.tracing.Tracer
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono
import java.util.*


class AppWebFilter(private val tracer: Tracer) : WebFilter {

    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        val currentSpan: Span? = tracer.currentSpan()
        if (currentSpan != null) {
            // ใส่ค่า trace id ใน [traceId] response header
            exchange.response.headers.add("traceId", currentSpan.context().traceId())
        }
        return chain.filter(exchange)
    }
}
