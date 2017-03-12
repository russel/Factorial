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

defaultTasks("test")
