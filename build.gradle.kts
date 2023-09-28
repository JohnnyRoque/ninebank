// Top-level build file where you can add configuration options common to all sub-projects/modules.
    plugins {

        alias(libs.plugins.com.android.application) apply false
        alias(libs.plugins.org.jetbrains.kotlin.android) apply false
    }


buildscript{
    dependencies{
        classpath(libs.androidx.androidx.navigation.safeargs.gradle.plugin)
    }
}
true // Needed to make the Suppress annotation work for the plugins block