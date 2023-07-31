package br.com.geshner.postalcodesearch.restapi

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url

private const val BASE_URL = "https://zipcloud.ibsnet.co.jp/api"
private const val END_POINT_SEARCH = "$BASE_URL/search"

class AddressApi {

    suspend fun findAddress(postalCode: String): AddressResponse {
        return ktorClient.get {
            url(END_POINT_SEARCH)
            parameter("zipcode", postalCode)
        }.body()
    }
}