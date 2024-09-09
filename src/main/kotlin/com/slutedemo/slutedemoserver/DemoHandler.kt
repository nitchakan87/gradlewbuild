package com.slutedemo.slutedemoserver


import com.slutedemo.slutedemoserver.model.TransferRequestOptional
import com.slutedemo.slutedemoserver.service.TestService
import kotlinx.coroutines.reactor.awaitSingle
import org.slf4j.LoggerFactory
import org.slf4j.MDC
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.awaitBody
import transferResponseSuccess

@Component
class DemoHandler(private val testService: TestService) {

    private val logger = LoggerFactory.getLogger(DemoHandler::class.java)

    suspend fun testEndpoint(request: ServerRequest): ServerResponse {
        val traceId = MDC.get("traceId") ?: "N/A"
        val spanId = MDC.get("spanId") ?: "N/A"
        logger.info("Handling /test server endpoint. traceId={}, spanId={}", traceId, spanId)

        val response = "ServerResponse Success! traceId=${traceId} , spanId= ${spanId}"
        return ok().bodyValue(response).awaitSingle()
    }

    suspend fun deactivateAccount(request: ServerRequest) =
        request.awaitBody<TransferRequestOptional<Void>>().let {
            transferResponseSuccess(
                it.headerRequest,
                testService.deactivateAccount()
            )
        }
}