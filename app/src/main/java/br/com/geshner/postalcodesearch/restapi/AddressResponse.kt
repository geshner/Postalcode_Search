package br.com.geshner.postalcodesearch.restapi

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddressResponse(
    @SerialName("status")
    val status: Int,
    @SerialName("message")
    val message: String?,
    @SerialName("results")
    val addresses: List<AddressDTO>?
)

@Serializable
data class AddressDTO(
    @SerialName("zipcode")
    val postalCode: String,
    @SerialName("address1")
    val address1: String,
    @SerialName("address2")
    val address2: String,
    @SerialName("address3")
    val address3: String,
    @SerialName("kana1")
    val kana1: String,
    @SerialName("kana2")
    val kana2: String,
    @SerialName("kana3")
    val kana3: String,
    @SerialName("prefcode")
    val prefCode: String
)
