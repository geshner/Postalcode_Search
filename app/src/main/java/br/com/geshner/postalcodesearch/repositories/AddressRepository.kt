package br.com.geshner.postalcodesearch.repositories

import android.util.Log
import br.com.geshner.postalcodesearch.domain.Address
import br.com.geshner.postalcodesearch.mapper.Mapper.toAddress
import br.com.geshner.postalcodesearch.restapi.AddressApi

class AddressRepository {

    private val addressApi = AddressApi()

    suspend fun findAddress(postalCode: String): List<Address> {
        return try {
            addressApi.findAddress(postalCode).addresses?.map {
                it.toAddress()
            } ?: emptyList()
        } catch (e: Exception) {
            Log.e("AddressRepository", "findAddress: ", e)
            emptyList()
        }
    }
}