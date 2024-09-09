package com.slutedemo.slutedemoserver.config

import io.micrometer.context.ContextSnapshotFactory
import io.netty.channel.ChannelDuplexHandler
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelPromise
import org.springframework.boot.web.embedded.netty.NettyServerCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
//import org.zalando.logbook.Logbook
//import org.zalando.logbook.netty.LogbookServerHandler
import reactor.netty.Connection

@Configuration
class ServerConfiguration {

    @Bean
    @Order(-1)
    fun logbookNettyServerCustomizer(contextSnapshotFactory: ContextSnapshotFactory): NettyServerCustomizer {
        return NettyServerCustomizer { server ->
            server.doOnConnection { connection: Connection ->
                connection.addHandlerLast(
                    CustomChannelDuplexHandler(contextSnapshotFactory)
                )
            }
        }
    }
}

class CustomChannelDuplexHandler(private val contextSnapshotFactory: ContextSnapshotFactory) : ChannelDuplexHandler() {
    override fun channelRead(ctx: ChannelHandlerContext, msg: Any) {
        contextSnapshotFactory.setThreadLocalsFrom<String>(ctx.channel()).use {
            //println(msg)
            ctx.fireChannelRead(msg)
        }
    }
    override fun write(ctx: ChannelHandlerContext, msg: Any, promise: ChannelPromise) {
        contextSnapshotFactory.setThreadLocalsFrom<String>(ctx.channel()).use {
            ctx.write(msg, promise)
        }
    }
}

