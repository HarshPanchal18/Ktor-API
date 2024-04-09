package com.example.repository

import com.example.security.JwtConfig
import com.example.service.CreateUserParams
import com.example.service.UserService
import com.example.utils.BaseResponse

class UserRepositoryImpl(
	private val userService: UserService
) : UserRepository {

	override suspend fun registerUser(params: CreateUserParams): BaseResponse<Any> {
		return if (isEmailExists(params.email)) {
			BaseResponse.ErrorResponse(message = "Email already exists")
		} else {
			val user = userService.registerUser(params)
			if (user != null) {
				val token = JwtConfig.instance.createAccessToken(user.id)
				user.authToken = token
				BaseResponse.Success(data = user)
			} else {
				BaseResponse.ErrorResponse()
			}
		}
	}

	private suspend fun isEmailExists(email: String): Boolean {
		return userService.findUserByEmail(email = email) != null
	}

}