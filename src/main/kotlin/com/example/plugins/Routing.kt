package com.example.plugins

import com.example.repository.UserRepository
import com.example.service.CreateUserParams
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
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

fun Application.configureAuthRoutes(userRepository: UserRepository) {
	routing {
		route("/auth") {
			post("/register") {
				val params = call.receive<CreateUserParams>()
				val result = userRepository.registerUser(params = params)
				call.respond(status = result.statusCode, message = result)
			}
		}
	}
}