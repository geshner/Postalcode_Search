package br.com.geshner.postalcodesearch.ui.uistates

import br.com.geshner.postalcodesearch.domain.Address

data class AddressSearchUiState(
    val isLoading: Boolean = false,
    val addressList: List<Address> = emptyList()
)
