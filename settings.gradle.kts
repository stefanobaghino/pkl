rootProject.name = "pkl"

include("bench")
include("docs")
include("stdlib")

include("pkl-certs")
include("pkl-cli")
include("pkl-codegen-java")
include("pkl-codegen-kotlin")
include("pkl-commons")
include("pkl-commons-cli")
include("pkl-commons-test")
include("pkl-config-java")
include("pkl-config-kotlin")
include("pkl-core")
include("pkl-doc")
include("pkl-gradle")
include("pkl-executor")
include("pkl-tools")
include("pkl-server")

pluginManagement {
  repositories {
    mavenCentral()
    gradlePluginPortal()
  }
}

plugins {
  id("com.gradle.develocity") version("3.17")
  id("com.gradle.common-custom-user-data-gradle-plugin") version("1.13")
}

develocity {
  server.set("https://ec2-54-225-44-244.compute-1.amazonaws.com")
  allowUntrustedServer.set(true)
  buildScan {
    publishing.onlyIf { true }
  }
}


@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
  repositories {
    mavenCentral()
  }
}

val javaVersion = JavaVersion.current()
require(javaVersion.isJava11Compatible) {
  "Project requires Java 11 or higher, but found ${javaVersion.majorVersion}."
}

if (gradle.startParameter.taskNames.contains("updateDependencyLocks") ||
  gradle.startParameter.taskNames.contains("uDL")
) {
  gradle.startParameter.isWriteDependencyLocks = true
}

for (prj in rootProject.children) {
  prj.buildFileName = "${prj.name}.gradle.kts"
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
