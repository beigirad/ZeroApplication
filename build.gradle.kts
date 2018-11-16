buildscript {
    repositories {
        google()
        jcenter()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:3.2.1")
        classpath(kotlin("gradle-plugin",version= "1.3.0"))
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
        maven("http://repository.jetbrains.com/all")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}