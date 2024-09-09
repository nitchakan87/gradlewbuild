package com.slutedemo.slutedemoserver.config

import io.micrometer.context.ContextSnapshotFactory
import io.netty.channel.ChannelDuplexHandler
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelPromise
import org.springframework.boot.autoconfigure.web.reactive.function.client.ReactorNettyHttpClientMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import reactor.netty.Connection

@Configuration
class ClientConfiguration {
    @Bean
    @Order(-1)
    fun logbookNettyClientCustomizer(contextSnapshotFactory: ContextSnapshotFactory): ReactorNettyHttpClientMapper {
        return ReactorNettyHttpClientMapper {
            it.doOnConnected { connection: Connection ->
                connection.addHandlerLast(
                    CustomChannelDuplexHandler(contextSnapshotFactory)
                )
            }
        }
    }
}
