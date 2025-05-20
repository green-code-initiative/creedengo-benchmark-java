plugins {
    `java-library`
    eclipse
    id("me.champeau.jmh") version "0.7.3"
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {
    // Use JUnit Jupiter for testing.
    testImplementation(libs.junit.jupiter)

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

// Apply a specific Java toolchain to ease working on different environments.
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

tasks.named<Test>("test") {
    // Use JUnit Platform for unit tests.
    useJUnitPlatform()
}

// The JMH task executes *all* the benchmarks.
// We prefer the `bench.sh` script to select benchmarks at a finer level.
jmh {
    benchmarkMode = listOf("avgt", "sample")
    timeUnit = "us"
    warmupIterations = 2
    iterations = 2
    fork = 1
    profilers = listOf("perfasm")
}

// Workaround https://github.com/redhat-developer/vscode-java/issues/1615
eclipse {
  classpath {
    baseSourceOutputDir = file("build")
  }
}
