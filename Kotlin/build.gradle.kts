import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	kotlin("jvm") version("1.4.10")
}

repositories {
	jcenter()
	mavenCentral()
}

dependencies {
	implementation(kotlin("stdlib"))
	testImplementation("io.kotest:kotest-runner-junit5-jvm:4.+")
	testImplementation("io.kotest:kotest-property-jvm:4.+")
}

tasks.withType<KotlinCompile>().configureEach {
	kotlinOptions.jvmTarget = 14.toString()
}

tasks.withType<Test> {
	useJUnitPlatform()
}

defaultTasks("test")
