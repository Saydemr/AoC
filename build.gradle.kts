plugins {
    kotlin("jvm") version "1.7.22"
    application
}

repositories {
    mavenCentral()
}

tasks {
    sourceSets {
        main {
            java.srcDirs("2022/src")
        }
    }

    wrapper {
        gradleVersion = "7.6"
    }
}

application {
    mainClass.set("Day12Kt")
}
