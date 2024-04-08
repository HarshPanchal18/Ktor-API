package com.example.repository

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
				// @TODO() Generate authentication tokens for the user
				BaseResponse.Success(data = user)
			} else {
				BaseResponse.ErrorResponse()
			}
		}
	}

	override suspend fun loginUser(email: String, password: String): BaseResponse<Any> {
		TODO("Not yet implemented")
	}

	private suspend fun isEmailExists(email: String): Boolean {
		return userService.findUserByEmail(email = email) != null
	}

}