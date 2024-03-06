package com.example.plugins.model

import kotlinx.serialization.Serializable

@Serializable
data class Customer(
    val id: String,
    val firstName: String,
    val lastName: String,
    val email: String
)

val customerStorage = mutableListOf<Customer>(
    Customer(id = "3", firstName = "Harsh", lastName = "Panchal", email = "harsh@gmail.com"),
    Customer(id = "5", firstName = "Dishang", lastName = "Rana", email = "dishang@gmail.com"),
)