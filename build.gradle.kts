// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false

    id("com.android.library") version "8.7.3" apply false
    id("com.google.devtools.ksp") version "2.1.0-1.0.29" apply false
}