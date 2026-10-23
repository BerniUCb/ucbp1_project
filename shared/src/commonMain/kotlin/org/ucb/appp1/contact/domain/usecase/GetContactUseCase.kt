package org.ucb.appp1.contact.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.ucb.appp1.contact.domain.model.ContactModel
import org.ucb.appp1.contact.domain.repository.ContactRepository

class GetContactUseCase(
    private val contactRepository: ContactRepository
) {
    operator fun invoke(): Flow<ContactModel> = contactRepository.observeContact()
}