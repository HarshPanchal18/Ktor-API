package com.example.security

import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.auth.Authentication
import io.ktor.server.auth.jwt.*

fun Application.configSecurity() {
	JwtConfig.initialize("my-story-app")
	install(Authentication) {
		jwt {
			verifier(JwtConfig.instance.verifier)
			validate {
				val claim = it.payload.getClaim(JwtConfig.CLAIM).asInt()
				if (claim != null) {
					UserIdPrinciple(claim)
				} else {
					null
				}
			}
		}
	}
}