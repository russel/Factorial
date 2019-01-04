plugins {
	kotlin("jvm") version("1.3.11")
}

repositories {
	jcenter()
	mavenCentral()
}

dependencies {
	compile(kotlin("stdlib"))
	// Need this for the KotlinTest to do the right thing.
	testCompile("org.jetbrains.kotlin:kotlin-reflect:1.3.11")
	testCompile("io.kotlintest:kotlintest-runner-junit5:3.+")
}

defaultTasks("test")
