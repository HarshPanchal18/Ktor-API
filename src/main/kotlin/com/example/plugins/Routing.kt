package com.example.plugins

import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.response.respondText
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {

        get("/") {
            call.respondText("Hello World!")
        }

        get("/harsh") {
            call.respondText("Harsh")
        }

        get("{id?}") {
            call.parameters["id"]?.let { value -> call.respondText(value) }
        }

    }
}