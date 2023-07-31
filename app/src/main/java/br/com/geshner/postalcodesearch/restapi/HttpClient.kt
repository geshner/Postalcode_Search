package br.com.geshner.postalcodesearch.restapi

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

private const val TIMEOUT_IN_MILLIS = 30_000 //30 seconds

val ktorClient = HttpClient(Android) {
    engine {
        connectTimeout = TIMEOUT_IN_MILLIS
        socketTimeout = TIMEOUT_IN_MILLIS
    }
    install(ContentNegotiation) {
        json(
            Json { ignoreUnknownKeys = true },
            ContentType.Text.Plain
        )
    }
    install(Logging) {
        level = LogLevel.ALL
    }
}