# Curved Bottom Navigation

[![JitPack](https://jitpack.io/v/Hamza-Bula/curved-bottom-navigation.svg)](https://jitpack.io/#Hamza-Bula/curved-bottom-navigation)
[![API](https://img.shields.io/badge/API-24%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=24)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](LICENSE)

A modern Android bottom navigation bar with a unique curved bubble design and smooth animations.

![Demo](art/demo.gif)

## Features

- **Curved bubble design** with elevation effect
- **Smooth animations** with customizable interpolators  
- **Highly customizable** colors, sizes, and animations
- **Simple API** with XML and programmatic configuration
- **Lightweight** with minimal dependencies

## Download

**Step 1:** Add JitPack repository to your root `settings.gradle.kts`:
```kotlin
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
Step 2: Add the dependency in build.gradle.kts:
kotlindependencies {
    implementation("com.github.Hamza-Bula:curved-bottom-navigation:1.0.0")
}
Usage
1. Create menu resource file res/menu/bottom_nav_menu.xml:
xml<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android">
    <item
        android:id="@+id/nav_home"
        android:icon="@drawable/ic_home"
        android:title="Home" />
    <item
        android:id="@+id/nav_dashboard"
        android:icon="@drawable/ic_dashboard"
        android:title="Dashboard" />
    <item
        android:id="@+id/nav_notifications"
        android:icon="@drawable/ic_notifications"
        android:title="Notifications" />
    <item
        android:id="@+id/nav_profile"
        android:icon="@drawable/ic_profile"
        android:title="Profile" />
</menu>
2. Add to your layout:
xml<com.hamza.curvedbottomnavigation.CustomBottomNavigationView
    android:id="@+id/bottom_navigation"
    android:layout_width="match_parent"
    android:layout_height="55dp"
    app:layout_constraintBottom_toBottomOf="parent" />
3. Setup in Activity:
kotlinval bottomNavigation = findViewById<CustomBottomNavigationView>(R.id.bottom_navigation)

// Set navigation items
bottomNavigation.setNavigationItems(
    listOf(
        NavItem(R.id.nav_home, R.drawable.ic_home, "Home"),
        NavItem(R.id.nav_dashboard, R.drawable.ic_dashboard, "Dashboard"),
        NavItem(R.id.nav_notifications, R.drawable.ic_notifications, "Notifications"),
        NavItem(R.id.nav_profile, R.drawable.ic_profile, "Profile")
    )
)

// Handle item selection
bottomNavigation.setOnItemSelectedListener(object : CustomBottomNavigationView.OnItemSelectedListener {
    override fun onItemSelected(itemId: Int) {
        when (itemId) {
            R.id.nav_home -> loadFragment(HomeFragment())
            R.id.nav_dashboard -> loadFragment(DashboardFragment())
            R.id.nav_notifications -> loadFragment(NotificationsFragment())
            R.id.nav_profile -> loadFragment(ProfileFragment())
        }
    }
})
Customization
XML Attributes
xml<com.hamza.curvedbottomnavigation.CustomBottomNavigationView
    android:id="@+id/bottom_navigation"
    android:layout_width="match_parent"
    android:layout_height="55dp"
    
    app:navBackgroundColor="#2E2E2E"
    app:selectedIconBackgroundColor="#FF5722"
    app:selectedIconColor="#FFFFFF"
    app:unselectedIconColor="#AAAAAA"
    app:borderColor="#FFFFFF"
    
    app:bubbleRadius="200dp"
    app:bubbleAnimationDuration="500"
    app:iconAnimationDuration="1000" />
Programmatically
kotlinbottomNavigation.apply {
    navBackgroundColor = Color.parseColor("#2E2E2E")
    selectedIconBackgroundColor = Color.parseColor("#FF5722")
    selectedIconColor = Color.WHITE
    bubbleAnimationDuration = 500L
}
Available Attributes
AttributeTypeDefaultDescriptionnavBackgroundColorcolor#2E2E2ENavigation bar backgroundselectedIconBackgroundColorcolor#2E2E2ESelected icon backgroundselectedIconColorcolor#FFFFFFSelected icon tintunselectedIconColorcolor#CCCCCCUnselected icon tintbubbleRadiusdimension200dpBubble sizeselectedIconSizedimension68dpSelected icon sizeunselectedIconSizedimension64dpUnselected icon sizebubbleAnimationDurationinteger500Animation duration (ms)
See attrs.xml for all attributes.
Requirements

Minimum SDK: 24

Sample
Check out the sample app for complete examples.
License
Copyright 2024-2025 Hamza Bula

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
Contributing
Contributions are welcome! Please feel free to submit a Pull Request.
Author
Hamza Bula - @Hamza-Bula
