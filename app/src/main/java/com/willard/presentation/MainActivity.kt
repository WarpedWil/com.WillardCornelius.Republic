package com.willard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface

import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.willard.presentation.components.DriversScreen
import com.willard.presentation.components.RouteScreen
import com.willard.presentation.viewmodels.DriverScreenViewModel
import com.willard.presentation.viewmodels.RouteViewModel
import com.willard.presentation.theme.DriverRoutesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DriverRoutesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    //  to remember the state of the navController across recompositions

                    val navController = rememberNavController()


                    NavHost(navController, startDestination = getString(R.string.driverScreenNavString)) {
                        composable(getString(R.string.driverScreenNavString)) {

                            // using hiltViewModel for hilt to work with jetpack nav controller

                            val viewModel = hiltViewModel<DriverScreenViewModel>()


                            DriversScreen(
                                viewModel = viewModel,
                                navController = navController
                            )
                        }

                        //sending the driver id to routes composable as arguments
                        composable(
                            "routeScreen/{driverId}",
                            arguments = listOf(navArgument(getString(R.string.driverIdString)) {
                                type = NavType.StringType
                            })
                        ) {

                            val viewModel = hiltViewModel<RouteViewModel>()


                            RouteScreen(
                                viewModel,
                                driverId = it.arguments?.getString(getString(R.string.driverIdString))
                                    ?: ""
                            )
                        }
                    }


                }
            }
        }
    }
}




