package com.address.validator.document

import com.google.cloud.firestore.annotation.DocumentId
import org.springframework.cloud.gcp.data.firestore.Document

@Document(collectionName = "address")
data class AddressDocument(
    @DocumentId
    val id: String = "",

    val unit: String,
    val street: String,
    val city: String,
    val province: String,
    val postalCode: String,
    val country: String
)
