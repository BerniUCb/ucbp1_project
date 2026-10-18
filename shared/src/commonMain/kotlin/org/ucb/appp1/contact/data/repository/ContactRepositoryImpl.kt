package org.ucb.appp1.contact.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import org.ucb.appp1.contact.data.datasource.RemoteConfigDataSource
import org.ucb.appp1.contact.domain.model.ContactModel
import org.ucb.appp1.contact.domain.repository.ContactRepository

class ContactRepositoryImpl(
    private val remoteConfigDataSource: RemoteConfigDataSource
) : ContactRepository {

    override fun observeContact(): Flow<ContactModel> = combine(
        remoteConfigDataSource.observePhone(),
        remoteConfigDataSource.observeEmail(),
        remoteConfigDataSource.observeWhatsappEnabled()
    ) { phone, email, whatsappEnabled ->
        ContactModel(
            phone = phone,
            email = email,
            whatsappEnabled = whatsappEnabled
        )
    }
}