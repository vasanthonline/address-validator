package com.address.validator.service

import com.address.validator.firestore.document.AddressDocument
import com.address.validator.models.Address
import com.address.validator.firestore.repository.AddressRepository
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Component
class AddressService(
    private val addressRepository: AddressRepository
) {
    fun createAddress(address: Address): Mono<AddressDocument> {
        return addressRepository.save(AddressDocument(
            unit = address.unit,
            street = address.street,
            city = address.city,
            province = address.province,
            postalCode = address.postalCode,
            country = address.country
        ))
    }

    fun getAddresses(): Flux<AddressDocument> {
        return addressRepository.findAll()
    }
}