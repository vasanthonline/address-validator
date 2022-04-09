package com.address.validator.repository

import com.address.validator.document.AddressDocument
import org.springframework.cloud.gcp.data.firestore.FirestoreReactiveRepository
import org.springframework.stereotype.Repository

@Repository
interface AddressRepository: FirestoreReactiveRepository<AddressDocument> {
}