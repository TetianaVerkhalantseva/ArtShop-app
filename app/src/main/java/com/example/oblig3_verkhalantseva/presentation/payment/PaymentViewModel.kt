package com.example.oblig3_verkhalantseva.presentation.payment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PaymentViewModel : ViewModel() {

    private val _confirmDialogIsVisibleState: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val confirmDialogIsVisibleState = _confirmDialogIsVisibleState.asStateFlow()

    fun setConfirmDialogVisibility(isVisible: Boolean) {
        viewModelScope.launch {
            _confirmDialogIsVisibleState.emit(isVisible)
        }
    }

}