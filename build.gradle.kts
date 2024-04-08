val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project

plugins {
	application
	kotlin("jvm") version "1.9.22"
	id("io.ktor.plugin") version "2.3.8"
	kotlin("plugin.serialization") version "1.9.0"
}

group = "com.example"
version = "0.0.1"

application {
	mainClass.set("com.example.ApplicationKt")

	val isDevelopment: Boolean = project.ext.has("development")
	applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
	google()
	mavenCentral()
}

dependencies {
	implementation("io.ktor:ktor-server-core-jvm")
	implementation("io.ktor:ktor-server-netty-jvm")
	implementation("io.ktor:ktor-serialization-kotlinx-json-jvm")
	implementation("io.ktor:ktor-serialization-gson-jvm")
	implementation("io.ktor:ktor-serialization-gson")
	implementation("io.ktor:ktor-serialization-jackson")
	implementation("io.ktor:ktor-serialization-jackson-jvm")
	implementation("io.ktor:ktor-server-content-negotiation-jvm")
	implementation("io.ktor:ktor-server-auth")
	implementation("io.ktor:ktor-server-auth-jwt")

	val exposeVersion = "0.37.3"
	implementation("org.jetbrains.exposed:exposed-core:$exposeVersion")
	implementation("org.jetbrains.exposed:exposed-dao:$exposeVersion")
	implementation("org.jetbrains.exposed:exposed-jdbc:$exposeVersion")
	implementation("org.jetbrains.exposed:exposed-java-time:$exposeVersion")

	implementation("ch.qos.logback:logback-classic:$logback_version")
	implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")

	testImplementation("io.ktor:ktor-server-tests-jvm")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit:1.9.0")
	testImplementation("io.ktor:ktor-server-test-host-jvm:2.3.8")

	implementation("org.postgresql:postgresql:42.6.0")
	implementation("com.zaxxer:HikariCP:5.0.1")
}