plugins {
    java
    application
    idea
    id("com.palantir.graal") version "0.7.2"
    id("io.freefair.lombok") version "5.3.0"
}

repositories {
    jcenter()
}

dependencies {
    implementation("com.google.guava:guava:29.0-jre")
    implementation("info.picocli:picocli:4.1.0")
    implementation("com.google.code.findbugs:jsr305:3.0.2")
    implementation("org.projectlombok:lombok:1.18.16")
    implementation("org.apache.commons:commons-lang3:3.11")
    implementation("org.apache.logging.log4j:log4j-core:2.14.0")

    annotationProcessor("info.picocli:picocli-codegen:4.1.0")

    testImplementation("org.assertj:assertj-core:3.12.2")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.2")

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.6.2")
}

application {
    mainClassName = "com.github.trevorwhitney.starter.App"
}

val test by tasks.getting(Test::class) {
    // Use junit platform for unit tests
    useJUnitPlatform()
    exclude("module-info.class")
    testLogging {
        events("passed", "skipped", "failed")
        exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
    }
}

tasks.withType(JavaCompile::class.java) {
    doFirst {
        options.compilerArgs = listOf("--module-path", classpath.asPath)
    }

    modularity.inferModulePath.set(true)
    sourceCompatibility = "11"
    targetCompatibility = "11"
}

//The graal gradle plugins doesn't support kotlin gradle syntax as of version 0.7.2
apply {
    from("$projectDir/graal.gradle")
}

configure<org.gradle.plugins.ide.idea.model.IdeaModel> {
    module {
        outputDir = File("$buildDir/classes/main")
        testOutputDir = File("$buildDir/classes/test")
    }
}