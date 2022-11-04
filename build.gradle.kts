plugins {
    java

    id("com.github.johnrengelman.shadow") version "7.1.2"
}

val buildNumber: String? = System.getenv("BUILD_NUMBER")

val jenkinsVersion = "1.8.3-b$buildNumber"

group = "com.badbones69.crazywarps"
version = "1.8.3"
description = "A clean, fancy and feature-rich warp plugin for your Minecraft Server!"

repositories {

    /**
     * PAPI Team
     */
    maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")

    /**
     * Spigot Team
     */
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")

    /**
     * NBT API
     */
    maven("https://repo.codemc.org/repository/maven-public/")

    /**
     * TriumphTeam
     */
    maven("https://repo.mattstudios.me/artifactory/public/")

    /**
     * Spigot Update Checker / Particles?
     */
    maven("https://hub.jeff-media.com/nexus/repository/jeff-media-public/")

    /**
     * Everything else we need.
     */
    maven("https://jitpack.io/")

    mavenCentral()
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.19.2-R0.1-SNAPSHOT")

    compileOnly("me.clip:placeholderapi:2.11.2") {
        exclude(group = "org.spigotmc", module = "spigot")
        exclude(group = "org.bukkit", module = "bukkit")
    }

    implementation("dev.triumphteam:triumph-gui:3.1.3")

    implementation("org.bstats:bstats-bukkit:3.0.0")

    implementation("com.jeff_media:SpigotUpdateChecker:3.0.0")
    implementation("xyz.xenondevs:particle:1.8.1")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

tasks {
    shadowJar {
        if (buildNumber != null) {
            archiveFileName.set("${rootProject.name}-[v${jenkinsVersion}].jar")
        } else {
            archiveFileName.set("${rootProject.name}-[v${rootProject.version}].jar")
        }

        listOf(
            "dev.triumphteam",
            "com.jeff_media",
            "org.bstats"
        ).forEach {
            relocate(it, "${rootProject.group}.plugin.lib.$it")
        }
    }

    compileJava {
        options.release.set(17)
    }

    processResources {
        filesMatching("plugin.yml") {
            expand(
                "name" to rootProject.name,
                "group" to rootProject.group,
                "version" to rootProject.version,
                "description" to rootProject.description
            )
        }
    }
}