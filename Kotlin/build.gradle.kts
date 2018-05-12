plugins {
	`kotlin-dsl`
}

repositories {
	jcenter()
	mavenCentral()
}

dependencies {
	compile(kotlin("stdlib"))
	testCompile("io.kotlintest:kotlintest-runner-junit5:3.+")
}

defaultTasks("test")
