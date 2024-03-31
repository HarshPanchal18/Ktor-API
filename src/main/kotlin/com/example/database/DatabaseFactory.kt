package com.example.database

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database

object DatabaseFactory {

	fun init() {
		Database.connect(hikari())
	}

	private fun hikari(): HikariDataSource {
		val config = HikariConfig().apply {
			driverClassName = "org.postgresql.Driver"
			jdbcUrl = "jdbc:postgresql:mystoryapp?user=Harsh&password=Harsh"
			maximumPoolSize = 3
			isAutoCommit = false
			transactionIsolation = "TRANSACTION_REPEATABLE_READ"
			validate()
		}
		return HikariDataSource(config)
	}

}