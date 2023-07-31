package br.com.geshner.postalcodesearch.mapper

import br.com.geshner.postalcodesearch.domain.Address
import br.com.geshner.postalcodesearch.restapi.AddressDTO

object Mapper {

    fun AddressDTO.toAddress() = Address(
        postalCode = postalCode,
        address1 = address1,
        address2 = address2,
        address3 = address3,
    )
}