package com.address.validator.models

data class Address(
    val id: String = "",
    val unit: String,
    val street: String,
    val city: String,
    val province: String,
    val postalCode: String,
    val country: String
)
