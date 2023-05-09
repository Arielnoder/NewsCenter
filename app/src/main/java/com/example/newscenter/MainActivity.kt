package com.example.newscenter

import android.os.Bundle
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.newscenter.Screen.Companion.items
import com.google.firebase.auth.FirebaseAuth


class MainActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser


        setContent {
           MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.primary) {

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
                // change start destination according to user login status
                // if (auth.currentUser != null) Screen.Home.route else
                startDestination =  Screen.LoginPage.route,

                Modifier.padding(innerPadding)
            ) {
                composable(Screen.LoginPage.route) { LoginPage(navController) }
                composable(Screen.Register.route) { Register(navController) }
                composable(Screen.SourceOne.route) { SourceOne(navController) }
                composable(Screen.Home.route) { Home(navController) }
                composable(Screen.SourceTwo.route) { SourceTwo(navController) }

            }
        }

    }
}









