package br.com.geshner.postalcodesearch.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.geshner.postalcodesearch.repositories.AddressRepository
import br.com.geshner.postalcodesearch.ui.uistates.AddressSearchUiState
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val addressRepository = AddressRepository()

    private var job : Job = Job()

    private val _uiState = MutableStateFlow(AddressSearchUiState())
    val uiState = _uiState.asStateFlow()

    fun findAddress(postalCode: String) {
        job.cancel()
        job = viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            val addressList = addressRepository.findAddress(postalCode)

            _uiState.update { it.copy(isLoading = false, addressList = addressList) }
        }
    }
}