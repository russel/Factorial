buildscript {
  repositories {
    gradleScriptKotlin()
  }
  dependencies {
    classpath(kotlinModule("gradle-plugin"))
  }
}

apply {
  plugin("kotlin")
}

repositories {
  gradleScriptKotlin()
  jcenter()
  mavenCentral()
}

dependencies {
  compile(kotlinModule("stdlib"))
  testCompile("io.kotlintest:kotlintest:1.+")
}

//targetCompatibility(8)

//task wrapper(type:Wrapper) {
//  gradleVersion = '3.2.1'
//}

defaultTasks("test")
