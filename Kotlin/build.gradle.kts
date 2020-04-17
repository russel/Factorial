plugins {
	kotlin("jvm") version("1.3.72")
}

repositories {
	jcenter()
	mavenCentral()
}

dependencies {
	compile(kotlin("stdlib"))
	testImplementation("io.kotlintest:kotlintest-runner-junit5:3.+")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

defaultTasks("test")
