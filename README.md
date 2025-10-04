# Curved Bottom Navigation

[![JitPack](https://jitpack.io/v/Hamza-Bula/curved-bottom-navigation.svg)](https://jitpack.io/#Hamza-Bula/curved-bottom-navigation)
[![API](https://img.shields.io/badge/API-24%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=24)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](LICENSE)

A modern Android bottom navigation bar with a unique curved bubble design and smooth animations.

![Demo](art/demo.gif)

Features

Curved bubble design with elevation effect
Smooth animations with customizable interpolators
Highly customizable colors, sizes, and animations
Simple API with XML and programmatic configuration
Lightweight with minimal dependencies
Supports 2-5 navigation items with automatic validation

Requirements

Minimum SDK: 24 (Android 7.0)

## Download

### Step 1: Add JitPack repository

Add JitPack repository to your root `settings.gradle.kts`:
```kotlin
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
Step 2: Add the dependency
Add the dependency in your app module's build.gradle.kts:
kotlindependencies {
    implementation("com.github.Hamza-Bula:curved-bottom-navigation:1.0.0")
}
