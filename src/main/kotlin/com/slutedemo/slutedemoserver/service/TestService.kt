package com.slutedemo.slutedemoserver.service

import com.slutedemo.slutedemoserver.DemoHandler
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class TestService {
    companion object{
        private val logger = LoggerFactory.getLogger(DemoHandler::class.java)
    }
    suspend fun deactivateAccount(): DeactivateAccountResponseDto {
        logger.info("deactivateAccount Service Server")
        return  DeactivateAccountResponseDto()
    }
}
class DeactivateAccountResponseDto