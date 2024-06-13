buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(libs.navigation.safe.args.gradle.plugin)
    }
}
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
}