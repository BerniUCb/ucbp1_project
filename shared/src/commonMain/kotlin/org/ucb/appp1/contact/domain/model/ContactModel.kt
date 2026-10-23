package org.ucb.appp1.contact.domain.model

data class ContactModel(
    val phone: String = "+591 7070812",
    val email: String = "soporte@empresa.com",
    val whatsappEnabled: Boolean = true
)