plugins {
    kotlin("jvm") version "2.3.10"
    alias(libs.plugins.koin.compiler)
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)
}

kotlin {
    jvmToolchain(25)
}
