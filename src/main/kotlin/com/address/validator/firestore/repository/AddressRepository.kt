package com.address.validator.firestore.repository

import com.address.validator.firestore.document.AddressDocument
import org.springframework.cloud.gcp.data.firestore.FirestoreReactiveRepository
import org.springframework.stereotype.Repository

@Repository
interface AddressRepository: FirestoreReactiveRepository<AddressDocument> {
}