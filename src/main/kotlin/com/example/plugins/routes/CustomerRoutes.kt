package com.example.plugins.routes

import com.example.plugins.model.Customer
import com.example.plugins.model.customerStorage
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.customerRouting() {

    route("/customer") {
        get {
            if (customerStorage.isNotEmpty()) {
                call.respond(customerStorage)
            } else {
                call.respondText("No customer found", status = HttpStatusCode.OK)
            }
        }

        get("{id?}") {
            val id =
                call.parameters["id"] ?: return@get call.respondText("Missing ID", status = HttpStatusCode.BadRequest)
            val customer = customerStorage.find { it.id == id } ?: return@get call.respondText(
                "No customer id with $id",
                status = HttpStatusCode.NotFound
            )
            call.respondText(customer.firstName)
            call.respond(customer.toString())
        }

        post {
            try {
                val customer = call.receive<Customer>() // deserialize the JSON request body into a Customer object
                customerStorage.add(customer)
                call.respondText("Customer stored successfully", status = HttpStatusCode.Created)
            } catch (e: ContentTransformationException) {
                call.respondText("Invalid request data", status = HttpStatusCode.BadRequest)
                application.log.error("Error deserializing customer data: ${e.message}")
            }
        }

        delete("{id?}") {
            val id =
                call.parameters["id"] ?: return@delete call.respondText(
                    text = "Missing ID",
                    status = HttpStatusCode.BadRequest
                )
            if (customerStorage.removeIf { it.id == id }) {
                call.respondText("Customer removed successfully", status = HttpStatusCode.Accepted)
            } else {
                call.respondText("Not Found", status = HttpStatusCode.NotFound)
            }
        }
    }
}