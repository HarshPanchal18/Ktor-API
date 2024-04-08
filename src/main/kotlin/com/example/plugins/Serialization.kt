package com.example.plugins

import io.ktor.http.*
import io.ktor.serialization.gson.*
import io.ktor.serialization.jackson.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import kotlinx.serialization.json.Json

fun Application.configureSerialization() {
	install(ContentNegotiation) {
		//gson()
		jackson()
		//jackson(ContentType.Application.FormUrlEncoded)
		//json(Json, contentType = ContentType.Application.FormUrlEncoded)
	}
}