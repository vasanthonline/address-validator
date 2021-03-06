package com.address.validator.web.handler

import com.address.validator.facade.AddressFacade
import com.address.validator.models.Address
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Component
class AddressHandler(
    private val addressFacade: AddressFacade
) {
    fun createAddress(serverRequest: ServerRequest): Mono<ServerResponse> {
        return serverRequest.bodyToMono(Address::class.java)
            .flatMap {
                addressFacade.saveAddress(it)
            }
            .flatMap {
                ServerResponse.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromValue(it))
            }
    }

    fun getAllAddresses(): Mono<ServerResponse> {
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(addressFacade.getAddresses(), Address::class.java)
    }
}