package com.example.newscenter

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.newscenter.ui.theme.NewsCenterTheme
import androidx.navigation.compose.rememberNavController
import com.example.newscenter.Screen.Companion.items



class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsCenterTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Main()

                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Main() {
        val navController = rememberNavController()


        Scaffold(
            bottomBar = {
                NavigationBar {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination
                    items.forEach { screen ->
                        NavigationBarItem(
                            icon = {
                                if(screen.route == Screen.Home.route) {
                                    Icon(Icons.Filled.Home, contentDescription = "Home")
                                }
                                else {
                                    Icon(Icons.Filled.Favorite, contentDescription = "Sources")
                                }
                            },
                            label = {
                                Text(stringResource(screen.resourceId))
                            },
                            selected = currentDestination?.let { it.route == screen.route } ?: false,
                            onClick = {
                                screen.route.let { route ->
                                    navController.navigate(route) {
                                        popUpTo(navController.graph.startDestinationId)
                                        launchSingleTop = true
                                    }
                                }
                            }
                        )
                    }
                }
            }
        ) { innerPadding ->
            NavHost(
                navController,
                startDestination = Screen.Home.route,
                Modifier.padding(innerPadding)
            ) {
                composable(Screen.SourceOne.route) { SourceOne(navController) }
                composable(Screen.Home.route) { Home(navController) }
                composable(Screen.SourceTwo.route) { SourceTwo(navController) }

            }
        }

    }
}









