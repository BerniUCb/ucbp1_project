package org.ucb.appp1.contact.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.ucb.appp1.contact.domain.model.ContactModel
import org.ucb.appp1.contact.domain.usecase.GetContactUseCase

class ContactViewModel(
    private val getContactUseCase: GetContactUseCase
) : ViewModel() {

    private val _contactState = androidx.lifecycle.ViewModel.viewModelScope.run {
        kotlinx.coroutines.flow.MutableStateFlow(
            ContactModel()
        )
    } as? kotlinx.coroutines.flow.MutableStateFlow<ContactModel>
        ?: kotlinx.coroutines.flow.MutableStateFlow(ContactModel())

    val contactState: StateFlow<ContactModel> = _contactState.asStateFlow()

    init {
        loadContact()
    }

    private fun loadContact() {
        viewModelScope.launch {
            getContactUseCase().collect { contact ->
                _contactState.value = contact
            }
        }
    }
}