package com.example

import com.example.database.DatabaseFactory
import com.example.plugins.*
import com.example.repository.UserRepository
import com.example.repository.UserRepositoryImpl
import com.example.service.UserService
import com.example.service.UserServiceImpl
import io.ktor.serialization.gson.*
import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.contentnegotiation.*
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

	val userService: UserService = UserServiceImpl()
	val userRepository = UserRepositoryImpl(userService)

	configureAuthRoutes(userRepository)
}