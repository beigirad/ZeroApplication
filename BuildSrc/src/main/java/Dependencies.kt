package ir.beigiead

object ApplicationId {
    val id = "ir.joojmooj"
}

object Modules {
    val cache = ":cache"
    val network = ":network"

    val presentation = ":presentation"

    val home = ":home"
    val login = ":login"
    val posts = ":posts"
}

object Releases {
    private val major = 0
    private val minor = 0
    private val patch = 1

    val versionCode = major * 10000 + minor * 100 + patch
    val versionName = "${major}.${minor}.${patch}"
}

object Versions {
    val gradle = "3.2.1"

    val compileSdk = 28
    val buildTools = "28.0.3"
    val minSdk = 21
    val targetSdk = 28

    val googleServices = "4.0.1"
    val firebase = "16.0.4"
    val googleAuth = "16.0.1"

    val fabric = "1.26.1"

    val appcompat = "1.0.1"
    val design = "1.0.0"
    val cardview = "1.0.0"
    val recyclerview = "1.0.0"
    val constraintlayout = "1.1.2"
    val maps = "15.0.1"

    val ktx = "1.0.0-alpha1"

    val kotlin = "1.3.10"
    val timber = "4.7.1"
    val rxkotlin = "2.3.0"
    val retrofit = "2.4.0"
    val loggingInterceptor = "3.11.0"
    val stethoInterceptor = "1.5.0"
    val glide = "4.8.0"
    val rxpaper = "1.2.0"
    val moshi = "1.4.0"
    val lifecycle = "2.0.0"
    val leakCanary = "1.6.2"
    val crashlytics = "2.9.6"
    val koin = "1.0.2"

    val junit = "4.12"
    val assertjCore = "3.11.1"
    val mockitoKotlin = "2.0.0-RC1"
    val mockitoInline = "2.23.0"
    val dagger = "2.16"
}

object Libraries {
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"

    val ktx = "androidx.core:core-ktx:${Versions.ktx}"

    val maps = "com.google.android.gms:play-services-maps:${Versions.maps}"

    val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    val rxkotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxkotlin}"

    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val rxjavaAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    val moshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptor}"
    val stethoInterceptor = "com.facebook.stetho:stetho-okhttp3:${Versions.stethoInterceptor}"

    val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"

    val rxpaper = "com.github.pakoito:RxPaper2:${Versions.rxpaper}"
    val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"

    val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"

    val leakCanaryAndroid = "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"
    val leakCanaryAndroidNoOp = "com.squareup.leakcanary:leakcanary-android-no-op:${Versions.leakCanary}"
    val leakCanaryAndroidSupportFragment = "com.squareup.leakcanary:leakcanary-support-fragment:${Versions.leakCanary}"

    val crashlytics = "com.crashlytics.sdk.android:crashlytics:${Versions.crashlytics}"

    val koinViewModel = "org.koin:koin-android-viewmodel:${Versions.koin}"

    val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    val daggerAndroid = "com.google.dagger:dagger-android:${Versions.dagger}"
    val daggerAndroidProcessor = "com.google.dagger:dagger-android-processor:${Versions.dagger}"
    val daggerAndroidSupport = "com.google.dagger:dagger-android-support:${Versions.dagger}"
}

object SupportLibraries {
    val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    val design = "com.google.android.material:material:${Versions.design}"
    val cardview = "androidx.cardview:cardview:${Versions.cardview}"
    val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
    val constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}"
}

object GoogleLibraries {
    val auth = "com.google.android.gms:play-services-auth:${Versions.googleAuth}"
}

object FirebaseLibraries {
    val auth = "com.google.firebase:firebase-auth:${Versions.firebase}"
    val core = "com.google.firebase:firebase-core:${Versions.firebase}"
}

object TestLibraries {
    val junit = "junit:junit:${Versions.junit}"
    val assertjCore = "org.assertj:assertj-core:${Versions.assertjCore}"
    val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoKotlin}"
    val mockitoInline = "org.mockito:mockito-inline:${Versions.mockitoInline}"
    val lifecycleTesting = "androidx.arch.core:core-testing:${Versions.lifecycle}"
}

object GradlePlugin {
    val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    val android = "com.android.tools.build:gradle:${Versions.gradle}"
}