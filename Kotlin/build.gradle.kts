plugins {
	`kotlin-dsl`
}

repositories {
	jcenter()
	mavenCentral()
}

tasks {
	withType<Test>{
		useJUnitPlatform()
	}
}

dependencies {
	compile(kotlin("stdlib"))
	testCompile("io.kotlintest:kotlintest-runner-junit5:3.+")
}

defaultTasks("test")
