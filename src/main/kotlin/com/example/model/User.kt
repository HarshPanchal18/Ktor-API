package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
	val id: Int,
	val fullName: String,
	val avatar: String,
	val email: String,
	var authToken: String? = null,
	val createdAt: String
)