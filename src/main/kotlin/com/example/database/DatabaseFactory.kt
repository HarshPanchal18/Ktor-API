package com.example.database

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {

	fun init() {
		Database.connect(hikari())
		transaction {
			SchemaUtils.create(UserTable) // create table only if it does not exist
		}
	}

	private fun hikari(): HikariDataSource {
		val config = HikariConfig().apply {
			driverClassName = "org.postgresql.Driver"
			username = "Harsh"
			password = "Harsh"
			jdbcUrl = "jdbc:postgresql:mystoryapp"
			maximumPoolSize = 3
			isAutoCommit = false
			transactionIsolation = "TRANSACTION_REPEATABLE_READ"
			validate()
		}
		return HikariDataSource(config)
	}

	suspend fun <T> dbQuery(block: () -> T): T = withContext(Dispatchers.IO) {
		transaction { block() }
	}
}