import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.plugin.SpringBootPlugin

plugins {

	// version create last
//	id("org.springframework.boot") version "3.2.8"
//	id("io.spring.dependency-management") version "1.1.6"
//	kotlin("jvm") version "1.9.24"
//	kotlin("plugin.spring") version "1.9.24"

	// version test old
//    id("org.springframework.boot") version "2.7.9"
//    id("io.spring.dependency-management") version "1.0.15.RELEASE"
//    kotlin("jvm") version "1.7.10"
//    kotlin("plugin.spring") version "1.4.32"
//
	/// version   project now
	id("org.springframework.boot") version "3.1.2"
	id("io.spring.dependency-management") version "1.1.2"
	kotlin("jvm") version "1.8.22"
	kotlin("plugin.spring") version "1.8.22"
}

group = "com.slutedemoserver"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}
val springCloudVersion = "2021.0.8"
object Versions{
	const val log4j = "2.18.0"
	const val log4jToSlf4j = "2.17.1"
	const val logback = "6.4"
}
repositories {
	mavenCentral {}
	maven { url = uri("https://repo.spring.io/milestone") }
}
dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion")
	}
}
dependencies {
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	//implementation("org.springframework.cloud:spring-cloud-starter-sleuth")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	//implementation("io.micrometer:micrometer-tracing-bridge-brave")
	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

//	implementation("net.logstash.logback:logstash-logback-encoder:5.1")
//	implementation("ch.qos.logback:logback-classic:1.5.6")
//	implementation("ch.qos.logback:logback-core:1.5.6")
//	implementation("io.micrometer:micrometer-tracing-bom:latest.release")
//	implementation ("io.micrometer:micrometer-tracing")
//	implementation("org.apache.logging.log4j:log4j-api:2.18.0")
//	implementation("org.apache.logging.log4j:log4j-to-slf4j:2.17.1")


	developmentOnly("org.springframework.boot:spring-boot-devtools")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.projectreactor:reactor-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

//	implementation("org.apache.logging.log4j:log4j-api:${Versions.log4j}")
//	implementation("org.apache.logging.log4j:log4j-to-slf4j:${Versions.log4jToSlf4j}")
//	implementation("org.apache.logging.log4j:log4j-to-slf4j:2.17.1")
	implementation("net.logstash.logback:logstash-logback-encoder:${Versions.logback}")
	//implementation("io.micrometer:micrometer-registry-prometheus")

	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
//	implementation("org.springframework.cloud:spring-cloud-function-core:3.2.6")
//	implementation("org.springframework.cloud:spring-cloud-function-context:3.2.6")
	//implementation("org.springframework.cloud:spring-cloud-starter-sleuth")
	//implementation("io.zipkin.zipkin2:zipkin:2.23.19")

	//implementation("io.netty:netty-resolver-dns-native-macos:4.1.76.Final:osx-aarch_64")

	implementation(platform(SpringBootPlugin.BOM_COORDINATES))
	implementation(platform("io.micrometer:micrometer-tracing-bom:1.2.5"))
//	implementation(platform("org.zalando:logbook-bom:3.8.0"))
	implementation("io.micrometer:micrometer-core")
	implementation("io.micrometer:micrometer-tracing-bridge-brave")
//	implementation("org.zalando:logbook-spring-boot-starter")
//	implementation("org.zalando:logbook-spring-boot-webflux-autoconfigure")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
