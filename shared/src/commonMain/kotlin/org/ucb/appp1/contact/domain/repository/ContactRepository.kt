package org.ucb.appp1.contact.domain.repository

import kotlinx.coroutines.flow.Flow
import org.ucb.appp1.contact.domain.model.ContactModel

interface ContactRepository {
    fun observeContact(): Flow<ContactModel>
}