package com.example.security

import io.ktor.util.hex
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import kotlin.text.toByteArray

val SECRET_KEY = "741852963"
val ALGORITHM = "HmacSHA1"
val HASH_KEY = hex(SECRET_KEY)
val HMAC_KEY = SecretKeySpec(HASH_KEY, ALGORITHM)

fun hash(password: String): String {
	val hmac = Mac.getInstance(ALGORITHM)
	hmac.init(HMAC_KEY)

	return hex(hmac.doFinal(password.toByteArray(Charsets.UTF_8))) // hashing password
}