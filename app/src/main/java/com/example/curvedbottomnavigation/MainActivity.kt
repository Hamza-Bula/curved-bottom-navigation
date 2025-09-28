package com.example.curvedbottomnavigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.hamza.curvedbottomnavigation.CustomBottomNavigationView
import com.hamza.curvedbottomnavigation.NavItem

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

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
                    R.id.navigation_home -> {
                        loadFragment(HomeFragment())
                    }
                    R.id.navigation_dashboard -> {
                        loadFragment(DashboardFragment())
                    }
                    R.id.navigation_notifications -> {
                        loadFragment(NotificationsFragment())
                    }
                    R.id.navigation_profile -> {
                        loadFragment(ProfileFragment())
                    }
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
            .commitAllowingStateLoss()
    }
}