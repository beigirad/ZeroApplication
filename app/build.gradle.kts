import ir.beigiead.dependencies.*
import java.util.*
import java.io.*

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
}


android {
    compileSdkVersion(Versions.compileSdk)
    buildToolsVersion(Versions.buildTools)

    defaultConfig {
        applicationId = ApplicationId.id
        minSdkVersion(Versions.minSdk)
        targetSdkVersion(Versions.targetSdk)
        versionCode = Releases.versionCode
        versionName = Releases.versionName

        vectorDrawables.useSupportLibrary = true
    }

    project.property("keystore.dir")?.also { dir ->
        Properties().also { it ->
            it.load(FileInputStream(file(dir)))

            signingConfigs {

                create("debugKey") {
                    storeFile = file(it["dStoreFile"]!!)
                    storePassword = it["dStorePassword"] as String
                    keyAlias = it["dKeyAlias"] as String
                    keyPassword = it["dKeyPassword"] as String
                }

                create("releaseKey") {
                    storeFile = file(it["rStoreFile"]!!)
                    storePassword = it["rStorePassword"] as String
                    keyAlias = it["rKeyAlias"] as String
                    keyPassword = it["rKeyPassword"] as String
                }
            }
        }
    }

    buildTypes {
        getByName("release") {
            isShrinkResources = true
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            signingConfig = signingConfigs.getByName("releaseKey")
        }

        getByName("debug") {
            applicationIdSuffix = ".debug"
            signingConfig = signingConfigs.getByName("debugKey")
        }
    }

    flavorDimensions("default")
    productFlavors {
        create("production") {
            flavorDimensions("default")
        }

        create("staging") {
            flavorDimensions("default")
            versionNameSuffix = "S"
        }
    }

}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(Libraries.kotlin)

    implementation(SupportLibraries.appcompat)
    implementation(SupportLibraries.design)
    implementation(SupportLibraries.constraintlayout)
    implementation(SupportLibraries.recyclerview)

    implementation(Libraries.retrofit)
    implementation(Libraries.gsonConverter)
    implementation(Libraries.stethoInterceptor)
    implementation(Libraries.rxjavaAdapter)

    implementation(Libraries.timber)

    implementation(Libraries.glide)
    kapt(Libraries.glideCompiler)
}