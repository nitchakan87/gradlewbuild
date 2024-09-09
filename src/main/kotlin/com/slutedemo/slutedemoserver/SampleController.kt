package com.slutedemo.slutedemoserver


import com.slutedemo.slutedemoserver.service.SampleService
import io.micrometer.context.ContextSnapshot
import io.micrometer.context.ContextSnapshotFactory
import io.micrometer.observation.contextpropagation.ObservationThreadLocalAccessor
import org.slf4j.LoggerFactory
import org.slf4j.MDC
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import reactor.util.context.Context
@RestController
class SampleController(private val sampleService: SampleService,private val contextSnapshotFactory: ContextSnapshotFactory) {
    private val logger = LoggerFactory.getLogger(SampleController::class.java)

    /**
     * Endpoint to return a greeting to the user. Demonstrates the use of path variable
     * and asynchronous processing in Spring WebFlux.
     *
     * @param name the name of the person to greet
     * @return a Mono containing the greeting message
     */
    @GetMapping(value = ["/greet/{name}"], produces = [MediaType.TEXT_PLAIN_VALUE])
    fun greet(@PathVariable name: String): Mono<String> {
        return sampleService.process(name)
            .map { processedName -> "Hello, $processedName!" }
    }

    @GetMapping("/server1/test")
    fun handleRequest(
        @RequestHeader("X-Trace-Id", required = false) traceId: String?
    ): Mono<String> {
        return Mono.deferContextual { contextView ->
            // สร้าง ContextSnapshot จาก context ปัจจุบัน
            ContextSnapshot.setThreadLocalsFrom(contextView, ObservationThreadLocalAccessor.KEY).use {
                // นำ traceId ไปใส่ใน MDC สำหรับการ logging
                traceId?.let { MDC.put("traceId", it) }

                // การทำงานที่ต้องใช้ traceId
                logger.info("Received request with Trace ID: $traceId")

                // สร้าง ContextSnapshot ใหม่ที่รวม traceId ไว้ใน context
                val newContext = Context.of("traceId", traceId!!)
                //val snapshot = contextSnapshotFactory.setThreadLocalsFrom()

                // ส่งต่อ ContextSnapshot นี้ใน Mono chain หรือ process ถัดไป
                Mono.just("Processed request with Trace ID: $traceId")
                    .contextWrite { it.putAll(newContext) } // ส่ง context นี้ต่อใน chain
            }
        }.doFinally {

        }
    }

}