package com.example

import com.example.database.DatabaseFactory
import com.example.plugins.configureAuthRoutes
import com.example.plugins.configureSerialization
import com.example.repository.UserRepositoryImpl
import com.example.security.configSecurity
import com.example.service.UserService
import com.example.service.UserServiceImpl
import io.ktor.server.application.*
import io.ktor.server.auth.authenticate
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.response.respond
import io.ktor.server.routing.*

fun main() {
	embeddedServer(
		Netty,
		port = 8080,
		host = "127.0.0.1",
		module = Application::module,
	).start(wait = true)
}

fun Application.module() {
	DatabaseFactory.init()
	configureSerialization()

	//configureRouting()

	configSecurity()

	val userService: UserService = UserServiceImpl()
	val userRepository = UserRepositoryImpl(userService)

	configureAuthRoutes(userRepository)

	routing {
		authenticate {
			get("/testurl") {
				call.respond("Working fine")
			}
		}
	}
}