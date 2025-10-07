plugins {
    java
	id("com.vanniktech.maven.publish") version "0.34.0"
}

group = "io.github.darvil82"
version = "0.7.1"
description = "Utilities for Java."

repositories {
    mavenCentral()
}

mavenPublishing {
	publishToMavenCentral()
	signAllPublications()
}

dependencies {
    implementation("org.jetbrains:annotations:24.1.0")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

mavenPublishing {
	coordinates(group.toString(), name.toString(), version.toString())

	pom {
		name.set("Java Utils")
		description.set(project.description)
		inceptionYear.set("2022")
		url.set("https://github.com/darvil82/java-utils")
		licenses {
			license {
				name.set("MIT License")
				url.set("https://opensource.org/license/mit")
				distribution.set("https://opensource.org/license/mit")
			}
		}
		developers {
			developer {
				id.set("darvil82")
				name.set("darvil82")
				url.set("https://github.com/darvil82/")
			}
		}
		scm {
			url.set("https://github.com/darvil82/java-utils")
			connection.set("scm:git:git://github.com/darvil82/java-utils.git")
			developerConnection.set("scm:git:ssh://git@github.com/darvil82/java-utils.git")
		}
	}
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
}

tasks.withType<Javadoc>().configureEach {
    options.encoding = "UTF-8"
}

tasks.test {
    useJUnitPlatform()
}