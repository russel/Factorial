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
  testCompile("io.kotlintest:kotlintest:2.+")
}

defaultTasks("test")
