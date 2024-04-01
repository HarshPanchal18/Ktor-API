package com.example.plugins

import com.example.repository.UserRepository
import com.example.service.CreateUserParams
import com.example.utils.BaseResponse
import io.ktor.http.*
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.reflect.*

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

fun Application.configureAuthRoutes(userRepository: UserRepository) {
	routing {
		route("/auth") {
			post("/register") {
				val params = call.receive<CreateUserParams>()
				val result = userRepository.registerUser(params = params)
				call.respond(message = result)
			}
		}
	}
}