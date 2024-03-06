package com.example.plugins

import io.ktor.http.*
import io.ktor.serialization.gson.*
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.contentnegotiation.*
import kotlinx.serialization.json.Json

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        gson {  }
        /*json(
            Json {
                ignoreUnknownKeys = true // Ignore Unknown keywords instead of throwing Exception
                isLenient = true // Quoted boolean literals, and unquoted string literals are allowed.
                encodeDefaults = true // Specifies whether default values of Kotlin properties should be encoded
                prettyPrint = true // Format the json
                coerceInputValues = true // Enables coercing incorrect JSON values to the default property value
            }
        )*/
    }

}