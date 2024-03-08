package com.example

import com.example.plugins.*
import io.ktor.server.application.Application
import io.ktor.server.engine.*
import io.ktor.server.netty.Netty

fun main() {
    embeddedServer(
        Netty,
        port = 8080,
        host = "127.0.0.1",
        module = Application::module,
    ).start(wait = true)
}

fun Application.module() {
    configureRouting()
    configureSerialization()
}