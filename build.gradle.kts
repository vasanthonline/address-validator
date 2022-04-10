import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension
import io.spring.gradle.dependencymanagement.dsl.ImportsHandler
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.3.3.RELEASE"
	id("io.spring.dependency-management") version "1.0.10.RELEASE"
	id("jacoco")
	id("java")

	id("org.sonarqube") version "3.0"
	id("idea")
	id("io.gatling.gradle") version "3.6.0"
	kotlin("jvm") version "1.3.72"
	kotlin("plugin.spring") version "1.3.72"
}

group = "com.address"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
	jcenter()
	maven {
		url = uri("https://maven-central.storage-download.googleapis.com/maven2/")
	}
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("com.google.auth:google-auth-library-oauth2-http:0.21.1")
	implementation("com.google.api-client:google-api-client:1.30.9")
	implementation("com.google.cloud:google-cloud-datastore:1.104.0")

	implementation("ch.qos.logback:logback-core:1.2.3")
	implementation("net.logstash.logback:logstash-logback-encoder:6.3")
	implementation("org.owasp:security-logging-logback:1.1.6")

	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.security:spring-security-jwt:1.0.10.RELEASE")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.cloud:spring-cloud-starter-kubernetes-config")
	implementation("org.springframework.cloud:spring-cloud-gcp-starter-data-firestore")

	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

	implementation("com.google.apis:google-api-services-cloudresourcemanager:v1-rev563-1.25.0")

	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
	implementation("org.jetbrains.kotlin:kotlin-reflect")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

configure<DependencyManagementExtension> {
	imports(delegateClosureOf<ImportsHandler> {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:Hoxton.SR8")
	})
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
