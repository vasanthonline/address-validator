package com.address.validator.facade

import com.address.validator.models.Address
import com.address.validator.service.AddressService
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Component
class AddressFacade(
    val addressService: AddressService
) {
    fun saveAddress(address: Address): Mono<Address> {
        val addressDocument = addressService.createAddress(
            Address(
                unit = address.unit,
                street = address.street,
                city = address.city,
                province = address.province,
                postalCode = address.postalCode,
                country = address.country
        ))
        return addressDocument.map {
            Address(
                unit = it.unit,
                street = it.street,
                city = it.city,
                province = it.province,
                postalCode = it.postalCode,
                country = it.country
            )
        }
    }
}