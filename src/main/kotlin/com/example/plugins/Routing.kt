package com.example.plugins

import com.example.repository.UserRepository
import com.example.service.CreateUserParams
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.Identity.decode
import io.ktor.util.reflect.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.buildJsonObject

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
				val createUserParams = CreateUserParams(
					fullName = "John Doe",
					email = "john.doe@exammple.com",
					password = "password123",
					avatar = "https://example.com/avatar.jpg"
				)

				//val json = Json { prettyPrint = true }
				//val jsonString = json.encodeToJsonElement(CreateUserParams.serializer(), createUserParams)
				//println(jsonString)
				//val params = call.receive<CreateUserParams>()
				val result = userRepository.registerUser(params = createUserParams)
				call.respond(status = result.statusCode, message = result)
			}
		}
	}
}