plugins {
    kotlin("jvm") version "1.9.21"
    id("io.qameta.allure") version "2.11.2"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.seleniumhq.selenium:selenium-java:4.21.0")
    implementation("org.junit.jupiter:junit-jupiter:5.8.1")
    implementation("io.appium:java-client:9.3.0")
    implementation("io.qameta.allure:allure-junit5:2.24.0")
    implementation("org.junit.jupiter:junit-jupiter-api:5.10.2")
    implementation("org.junit.jupiter:junit-jupiter-engine:5.10.2")
    implementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    testLogging {
        showStandardStreams = true // This will show System.out.println() in the console
        outputs.upToDateWhen {false}
    }
    systemProperty("platform", System.getProperty("platform"))
    filter {
        val platform = System.getProperty("platform") ?: "Android"
        includeTestsMatching(when (platform) {
            "Android" -> "tests.*"
            "iOS" -> "tests.*"
            else -> throw IllegalArgumentException("Unsupported platform: $platform")
        })
    }
    useJUnitPlatform()
}


kotlin {
    jvmToolchain(17)
}