package com.address.validator.web

import com.address.validator.models.Address
import com.address.validator.web.handler.AddressHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.BodyInserters
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
            GET("/address") {
                val address = Address("", "", "", "", "", "", "")
                ServerResponse.ok()
                    .contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(address))
            }
            PUT("/address") {
                addressHandler.createAddress(it)
            }
        }
    }
}