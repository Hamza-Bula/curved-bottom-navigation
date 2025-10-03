Features

Curved bubble design with elevation effect
Smooth animations with customizable interpolators
Highly customizable colors, sizes, and animations
Simple API with XML and programmatic configuration
Lightweight with minimal dependencies
Supports 2-5 navigation items with automatic validation

Requirements

Minimum SDK: 24 (Android 7.0)

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
1. Create menu resource file
Create a menu file at res/menu/bottom_nav_menu.xml:
xml<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android">
    <item
        android:id="@+id/navigation_home"
        android:icon="@drawable/baseline_home_24"
        android:title="Home" />
    <item
        android:id="@+id/navigation_dashboard"
        android:icon="@drawable/baseline_dashboard_24"
        android:title="Dashboard" />
    <item
        android:id="@+id/navigation_notifications"
        android:icon="@drawable/baseline_notifications_24"
        android:title="Notifications" />
    <item
        android:id="@+id/navigation_profile"
        android:icon="@drawable/baseline_person_24"
        android:title="Profile" />
</menu>
2. Add to your layout
Add the CustomBottomNavigationView to your layout XML:
xml<com.hamza.curvedbottomnavigation.CustomBottomNavigationView
    android:id="@+id/custom_bottom_navigation"
    android:layout_width="match_parent"
    android:layout_height="55dp"
    app:layout_constraintBottom_toBottomOf="parent" />
3. Setup in MainActivity
kotlinclass MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigation = findViewById<CustomBottomNavigationView>(R.id.custom_bottom_navigation)

        bottomNavigation.setNavigationItems(listOf(
            NavItem(R.id.navigation_home, R.drawable.baseline_home_24, "Home"),
            NavItem(R.id.navigation_dashboard, R.drawable.baseline_dashboard_24, "Dashboard"),
            NavItem(R.id.navigation_notifications, R.drawable.baseline_notifications_24, "Notifications"),
            NavItem(R.id.navigation_profile, R.drawable.baseline_person_24, "Profile")
        ))

        bottomNavigation.setOnItemSelectedListener(object : CustomBottomNavigationView.OnItemSelectedListener {
            override fun onItemSelected(itemId: Int) {
                when (itemId) {
                    R.id.navigation_home -> loadFragment(HomeFragment())
                    R.id.navigation_dashboard -> loadFragment(DashboardFragment())
                    R.id.navigation_notifications -> loadFragment(NotificationsFragment())
                    R.id.navigation_profile -> loadFragment(ProfileFragment())
                }
            }
        })

        if (savedInstanceState == null) {
            loadFragment(HomeFragment())
            bottomNavigation.setSelectedItem(R.id.navigation_home)
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}
Customization
XML Attributes
Customize the appearance directly in your layout XML:
xml<com.hamza.curvedbottomnavigation.CustomBottomNavigationView
    android:id="@+id/custom_bottom_navigation"
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
You can also customize properties programmatically:
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
AttributeTypeDefaultDescriptionnavBackgroundColorcolor#2E2E2ENavigation bar background colorselectedIconBackgroundColorcolor#2E2E2ESelected icon background circle colorselectedIconColorcolor#FFFFFFSelected icon tint colorunselectedIconColorcolor#CCCCCCUnselected icon tint colorborderColorcolor#FFFFFFTop border line color
Dimensions
AttributeTypeDefaultDescriptionnavHeightdimension200dpNavigation bar heightbubbleRadiusdimension200dpBubble radius sizeselectedIconSizedimension68dpSelected icon sizeunselectedIconSizedimension64dpUnselected icon sizeselectedIconBackgroundRadiusdimension46dpSelected icon background circle radiusborderStrokeWidthdimension3dpTop border stroke widthselectedIconYdimension50dpSelected icon Y position from top
Bubble Shape Properties
AttributeTypeDefaultDescriptionbubbleWidthMultiplierfloat2.0Controls bubble width relative to radiusbubbleHeightFactorfloat0.6Controls bubble curve depthbubbleCurveFactorfloat0.2Controls curve smoothnessbubbleEdgeFactorfloat0.4Controls bubble edge width
Animation Properties
AttributeTypeDefaultDescriptionbubbleAnimationDurationinteger500Bubble movement animation duration (ms)iconAnimationDurationinteger1000Icon animation duration (ms)iconScaleMinfloat0.3Minimum icon scale during animationiconScaleMaxfloat1.0Maximum icon scale after animationiconScaleOvershootfloat1.5Icon scale overshoot amounticonPositionOvershootfloat1.2Icon position overshoot amount
See attrs.xml for the complete list of available attributes.
Important Notes

The library supports 2 to 5 navigation items
Less than 2 items: Warning shown, but will work
More than 5 items: Only first 5 items used, warning shown

Sample App
Check out the sample app for complete examples and implementation details.
Troubleshooting
Icons not showing
Make sure your drawable resources exist in res/drawable/:
res/
  drawable/
    baseline_home_24.xml
    baseline_dashboard_24.xml
    baseline_notifications_24.xml
    baseline_person_24.xml
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
