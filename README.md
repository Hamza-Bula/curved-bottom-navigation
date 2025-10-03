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

Download
Step 1: Add JitPack repository
Add JitPack repository to your root settings.gradle.kts:
kotlindependencyResolutionManagement {
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
Usage
1. Add to your layout
Add the CustomBottomNavigationView to your layout XML:
xml<com.hamza.curvedbottomnavigation.CustomBottomNavigationView
    android:id="@+id/bottom_navigation"
    android:layout_width="match_parent"
    android:layout_height="55dp"
    app:layout_constraintBottom_toBottomOf="parent" />
2. Setup in Activity
Initialize and configure the navigation in your Activity:
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

// Optionally set initial selected item
bottomNavigation.setSelectedItem(R.id.nav_home)
3. Fragment loading helper
Create a helper method to load fragments:
kotlinprivate fun loadFragment(fragment: Fragment) {
    supportFragmentManager.beginTransaction()
        .replace(R.id.fragment_container, fragment)
        .commit()
}
Customization
XML Attributes
Customize the appearance using XML attributes:
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
    app:selectedIconSize="68dp"
    app:unselectedIconSize="64dp"
    
    app:bubbleAnimationDuration="500"
    app:iconAnimationDuration="1000" />
Programmatic Customization
You can also customize the navigation programmatically:
kotlinbottomNavigation.apply {
    navBackgroundColor = Color.parseColor("#2E2E2E")
    selectedIconBackgroundColor = Color.parseColor("#FF5722")
    selectedIconColor = Color.WHITE
    unselectedIconColor = Color.LTGRAY
    borderColor = Color.WHITE
    
    bubbleRadius = 200f
    selectedIconSize = 68
    unselectedIconSize = 64
    
    bubbleAnimationDuration = 500L
    iconAnimationDuration = 1000L
}
Available Attributes
Colors
AttributeTypeDefaultDescriptionnavBackgroundColorcolor#2E2E2ENavigation bar background colorselectedIconBackgroundColorcolor#2E2E2ESelected icon background colorselectedIconColorcolor#FFFFFFSelected icon tint colorunselectedIconColorcolor#CCCCCCUnselected icon tint colorborderColorcolor#FFFFFFTop border color
Dimensions
AttributeTypeDefaultDescriptionnavHeightdimension200dpNavigation bar heightbubbleRadiusdimension200dpBubble radius sizeselectedIconSizedimension68dpSelected icon sizeunselectedIconSizedimension64dpUnselected icon sizeselectedIconBackgroundRadiusdimension46dpSelected icon background circle radiusborderStrokeWidthdimension3dpTop border stroke widthselectedIconYdimension50dpSelected icon Y position
Bubble Shape Properties
AttributeTypeDefaultDescriptionbubbleWidthMultiplierfloat2.0Bubble width multiplierbubbleHeightFactorfloat0.6Bubble height factorbubbleCurveFactorfloat0.2Bubble curve factorbubbleEdgeFactorfloat0.4Bubble edge factor
Animation Properties
AttributeTypeDefaultDescriptionbubbleAnimationDurationinteger500Bubble animation duration (ms)iconAnimationDurationinteger1000Icon animation duration (ms)iconScaleMinfloat0.3Minimum icon scaleiconScaleMaxfloat1.0Maximum icon scaleiconScaleOvershootfloat1.5Icon scale overshoot factoriconPositionOvershootfloat1.2Icon position overshoot factor
See attrs.xml for all available attributes.
Requirements

Minimum SDK: 24 (Android 7.0)
Target SDK: 35
Kotlin: 2.0.21+
AndroidX: Required

Sample App
Check out the sample app for complete examples and implementation details.
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

Fork the repository
Create your feature branch (git checkout -b feature/AmazingFeature)
Commit your changes (git commit -m 'Add some AmazingFeature')
Push to the branch (git push origin feature/AmazingFeature)
Open a Pull Request

Author
Hamza Bula - @Hamza-Bula
Support
If you find this library useful, please consider giving it a ⭐ on GitHub!
For issues, questions, or suggestions, please open an issue.
