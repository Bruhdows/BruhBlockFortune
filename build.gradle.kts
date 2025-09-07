plugins {
    id("java")
    id("com.gradleup.shadow") version "9.1.0"
    id("io.freefair.lombok") version "9.0.0-rc2"
}

group = "com.bruhdows"
version = "1.0"

repositories {
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://storehouse.okaeri.eu/repository/maven-public/")
    maven("https://repo.panda-lang.org/releases")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.4-R0.1-SNAPSHOT")
    implementation("eu.okaeri:okaeri-configs-yaml-bukkit:5.0.13")
    implementation("eu.okaeri:okaeri-configs-serdes-bukkit:5.0.13")
    implementation("dev.rollczi:litecommands-bukkit:3.10.5")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}