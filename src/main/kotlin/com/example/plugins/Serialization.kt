package com.example.plugins

import com.example.database.DatabaseFactory
import com.example.service.CreateUserParams
import io.ktor.http.*
import io.ktor.serialization.gson.*
import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*

fun Application.configureSerialization() {
    DatabaseFactory.init()
    install(ContentNegotiation) {
        //gson(ContentType.Application.Any)
        jackson(ContentType.Application.FormUrlEncoded)
    }
}