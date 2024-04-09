package com.example.security

import io.ktor.server.auth.Principal

data class UserIdPrinciple(val id: Int): Principal