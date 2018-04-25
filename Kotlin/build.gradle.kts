plugins {
	`kotlin-dsl`
}

repositories {
	jcenter()
	mavenCentral()
}

dependencies {
	compile(kotlin("stdlib"))
	testCompile("io.kotlintest:kotlintest:2.+")
}

defaultTasks("test")
