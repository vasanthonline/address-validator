package com.address.validator.web.config

import com.address.validator.web.handler.AddressHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.router
import org.springframework.web.reactive.function.server.ServerResponse

@Configuration
class AddressConfiguration(
    val addressHandler: AddressHandler
) {
    @Bean
    fun addressRouter(): RouterFunction<ServerResponse> {
        return router {
            BASE_URI.nest {
                PUT("/").invoke { addressHandler.createAddress(it) }
            }
        }
    }
    companion object {
        const val BASE_URI = "/address"
    }
}