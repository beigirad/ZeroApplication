import com.android.build.gradle.internal.dsl.SigningConfig
import ir.beigiead.*

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
    }

    buildTypes {
        getByName("release") {
            isShrinkResources = true
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
//            signingConfig = SigningConfigs.releaseKey
        }

        getByName("debug") {
            applicationIdSuffix = ".debug"
//            signingConfig = SigningConfigs.debugKey
        }
    }

}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(SupportLibraries.appcompat)
    implementation(SupportLibraries.design)
    implementation(SupportLibraries.constraintlayout)

    implementation(Libraries.retrofit)
    implementation(Libraries.moshiConverter)
    implementation(Libraries.rxjavaAdapter)

    implementation(Libraries.timber)



    implementation(Libraries.kotlin)

}