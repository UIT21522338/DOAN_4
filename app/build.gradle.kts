plugins {
    alias(libs.plugins.androidApplication)

}

android {
    namespace = "com.example.doan"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.doan"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation ("com.github.PhilJay:MPAndroidChart:v3.1.0");


    implementation("com.github.bumptech.glide:glide:4.12.0");
    implementation("com.google.code.gson:gson:2.9.1");
    implementation("com.tbuonomo:dotsindicator:5.0");

    // RxJava
    implementation("io.reactivex.rxjava3:rxandroid:3.0.0");
    implementation("io.reactivex.rxjava3:rxjava:3.0.0");

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0");
    implementation("com.squareup.retrofit2:converter-gson:2.9.0");
    implementation("com.github.akarnokd:rxjava3-retrofit-adapter:3.0.0");

    // Chart
}

android{
    useLibrary("org.apache.http.legacy")
}