val version = "1"

val gradle_version = "4.9"
val kotlin_version = "1.2.51"
val spark_version = "2.7.2"
val slf4j_version = "1.8.0-beta2"
val spek_version = "1.1.5"
val junit_platform_version = "1.3.0-M1"
val kluent_version = "1.32"

val mainClass = "jks.postnummer.RunnerKt"

plugins {
   application
   kotlin("jvm") version "1.2.51"
}

buildscript {
   dependencies {
      classpath("org.junit.platform:junit-platform-gradle-plugin:1.2.0")
   }
}

application {
   mainClassName = "$mainClass"
}

dependencies {
   compile("org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version")
   compile("com.sparkjava:spark-core:$spark_version")
   compile("org.slf4j:slf4j-simple:$slf4j_version")

   testCompile("org.jetbrains.spek:spek-api:$spek_version") {
      exclude(group = "org.jetbrains.kotlin")
   }
   testCompile("org.jetbrains.kotlin:kotlin-reflect:$kotlin_version")
   testCompile("org.amshove.kluent:kluent:$kluent_version")
   testRuntime("org.junit.platform:junit-platform-runner:$junit_platform_version")
   testRuntime("org.jetbrains.spek:spek-junit-platform-engine:$spek_version") {
      exclude(group = "org.jetbrains.kotlin")
   }
}

repositories {
   jcenter()
   mavenCentral()
   maven("https://repo.adeo.no/repository/maven-releases/")
}

java {
   sourceCompatibility = JavaVersion.VERSION_1_10
   targetCompatibility = JavaVersion.VERSION_1_10
}

tasks.withType<Test> {
   useJUnitPlatform()
   testLogging {
      events("passed", "skipped", "failed")
   }
}

tasks.withType<Wrapper> {
   gradleVersion = "4.9"
}

val fatJar = task("fatJar", type = Jar::class) {
   baseName = "${project.name}-${version}-all"
   manifest {
      attributes["Implementation-Title"] = "postnummer"
      attributes["Main-Class"] = "$mainClass"
   }
   from(configurations.runtime.map({ if (it.isDirectory) it else zipTree(it) }))
   with(tasks["jar"] as CopySpec)
}


