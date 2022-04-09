package com.address.validator.service

import com.address.validator.document.AddressDocument
import com.address.validator.models.Address
import com.address.validator.repository.AddressRepository
import org.springframework.stereotype.Component
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
}