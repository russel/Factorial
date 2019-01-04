val kotlinVersion = "1.3.11"

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
	testCompile("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
	// There seems to be a cock up in the Kotlin internal dependencies so we have to do this. :-(
	testCompile("org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersion")
	testCompile("io.kotlintest:kotlintest-runner-junit5:3.+")
}

defaultTasks("test")
