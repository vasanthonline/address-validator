package com.address.validator.web

import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.gcp.data.firestore.mapping.FirestoreMappingContext
import org.springframework.cloud.gcp.data.firestore.mapping.FirestorePersistentEntity
import org.springframework.cloud.gcp.data.firestore.mapping.FirestorePersistentEntityImpl
import org.springframework.cloud.gcp.data.firestore.repository.config.EnableReactiveFirestoreRepositories
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.util.TypeInformation

@Configuration
@EnableReactiveFirestoreRepositories(basePackages = ["com.address.validator.firestore"])
class FirestoreConfiguration(@Value("\${firestore.collection.prefix}") val collectionPrefix: String) {

    @Bean
    fun firestoreMappingContext(): FirestoreMappingContext {
        return AddressFirestoreMappingContext(collectionPrefix)
    }
}

class AddressFirestoreMappingContext(private val collectionPrefix: String) : FirestoreMappingContext() {
    override fun <T : Any> createPersistentEntity(typeInformation: TypeInformation<T>): FirestorePersistentEntity<*> {
        return PrefixedFirestorePersistenceEntity(typeInformation, collectionPrefix)
    }
}

class PrefixedFirestorePersistenceEntity<T>(information: TypeInformation<T>,
                                            private val collectionPrefix: String = "local") : FirestorePersistentEntityImpl<T>(information) {
    override fun collectionName(): String {
        val userName = System.getProperty("user.name") ?: ""
        return when {
            collectionPrefix.equals("local", ignoreCase = true) ->
                collectionPrefix.toUpperCase() + "_" + userName.toUpperCase() + "_" + super.collectionName()
            else -> collectionPrefix.toUpperCase() + "_" + super.collectionName()
        }
    }
}