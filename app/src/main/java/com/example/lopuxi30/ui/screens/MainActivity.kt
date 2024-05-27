package com.example.lopuxi30.ui.screens

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.navOptions
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.lopuxi30.R
import com.example.lopuxi30.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val appBarConfiguration: AppBarConfiguration = AppBarConfiguration.Builder(
            R.id.feedFragment, R.id.createPostFragment
        ).build()

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment?
        val navController = navHostFragment!!.navController
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.bottomNavigation, navController)

        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.feedFragment -> {
                    navController.navigate(R.id.feedFragment, null, navOptions {
                        popUpTo(R.id.nav_graph_xml) { inclusive = true }
                    })
                    true
                }

                R.id.createPostFragment -> {
                    navController.navigate(R.id.createPostFragment, null, navOptions {
                        popUpTo(R.id.nav_graph_xml) { inclusive = true }
                    })
                    true
                }

                else -> false
            }
        }
    }

    fun showBottomNavigationView() {
        binding.bottomNavigation.visibility = View.VISIBLE
    }
}