apply plugin: 'com.android.application'
apply plugin: 'kotlin-android'
apply plugin: 'kotlin-android-extensions'
apply plugin: 'kotlin-kapt'

android {
    compileSdkVersion rootProject.ext.compileSdkVersion
    buildToolsVersion rootProject.ext.buildToolsVersion

    flavorDimensions "default"

    def majorVersion = 0
    def minorVersion = 0
    def patchVersion = 1

    defaultConfig {
        applicationId "ir.beigirad"

        minSdkVersion rootProject.ext.minSdkVersion
        targetSdkVersion rootProject.ext.targetSdkVersion

        versionCode majorVersion * 10000 + minorVersion * 100 + patchVersion
        versionName "${majorVersion}.${minorVersion}.${patchVersion}"

        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
    }

    if (project.hasProperty("myproject.properties")) {
        Properties props = new Properties()
        props.load(new FileInputStream(file(project.property("myproject.properties"))))
        signingConfigs {
            releaseKey {
                storeFile file(props['storeFile'])
                storePassword props['storePassword']
                keyAlias props['keyAlias']
                keyPassword props['keyPassword']
            }
            debugKey {
                storeFile file(props['storeFile'])
                storePassword props['storePassword']
                keyAlias props['keyAlias']
                keyPassword props['keyPassword']
            }
        }
    }

    buildTypes {
        release {
            shrinkResources true
            minifyEnabled true
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
            signingConfig signingConfigs.debugKey
        }

        debug {
            signingConfig signingConfigs.debugKey
            applicationIdSuffix ".debug"
        }
    }

    productFlavors {
        normal {
        }
        demo {
            applicationIdSuffix ".demo"
        }
    }





    applicationVariants.all { variant ->
        def appName = rootProject.name
        variant.outputs.all { output ->
            def newApkName = "${appName}-${output.baseName}-${variant.versionName}.apk".toLowerCase()
            def newApkDir = output.packageApplication.outputDirectory.toPath().relativize(rootDir.toPath()).toFile()
            newApkDir = "../" //override on baseDirectory
            output.outputFileName = new File(newApkDir, newApkName)
        }
    }


    splits {
        abi {
            enable false
            reset()
            include "armeabi-v7a"
        }
    }


}

dependencies {
    implementation rootProject.ext.libraries.appCompat
    implementation rootProject.ext.libraries.design
    implementation rootProject.ext.libraries.support
    implementation rootProject.ext.libraries.cardView

    implementation rootProject.ext.libraries.calligraphy
    implementation rootProject.ext.libraries.lovelyDialog


    implementation rootProject.ext.libraries.retrofit
    implementation rootProject.ext.libraries.gsonConvertor
    implementation rootProject.ext.libraries.stethoOkhttp

    implementation rootProject.ext.kotlinDependencyJdk

}
repositories {
    mavenCentral()
}
