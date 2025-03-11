# Java Utilities

A collection of some of my utility classes.

## Installation

The package is currently available on Repsy and GitHub Packages.

1. Add the following to your `repositories` block:
	```kotlin
	maven("https://api.repsy.io/mvn/darvil/java")
	```
 
2. And add the following to your `dependencies` block:
	```kotlin
	implementation("com.darvil:utils:+")
	```
> [!NOTE]
> The `+` symbol is a wildcard that will automatically use the latest version of the package.
> You can also specify a specific version (e.g. `0.0.1`).
