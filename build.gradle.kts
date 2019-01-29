buildscript {
    repositories {
        google()
        jcenter()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:3.2.1")
        classpath(kotlin("gradle-plugin",version= "1.3.20"))
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
        maven ( "https://jitpack.io" )
        maven("http://repository.jetbrains.com/all")
        maven("https://plugins.gradle.org/m2/")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}