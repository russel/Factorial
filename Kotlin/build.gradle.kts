val kotlinVersion = "1.3.0"

plugins {
	kotlin("jvm") version("1.3.0")
}

repositories {
	jcenter()
	mavenCentral()
}

dependencies {
	compile(kotlin("stdlib"))
	testCompile("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion") // Why is this needed?
	testCompile("org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersion") // Why is this needed?
	testCompile("io.kotlintest:kotlintest-runner-junit5:3.+")
}

defaultTasks("test")
