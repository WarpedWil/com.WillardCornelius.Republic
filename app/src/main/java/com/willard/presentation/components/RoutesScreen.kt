package com.willard.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.willard.presentation.viewmodels.DriverRouteState
import com.willard.presentation.viewmodels.RouteViewModel

@Composable
fun RouteScreen(
    viewModel: RouteViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    driverId: String = ""
) {

    val routeState by viewModel.driverRouteState

    LaunchedEffect(key1 = Unit, block = {
        viewModel.getRouteForDriver(driverId)

    })

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Route") }
            )
        }

    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {

            routeState.let { routeValue ->
                when (routeValue) {
                    is DriverRouteState.Loading -> {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .size(30.dp)
                                .align(Alignment.Center)
                        )
                    }
                    is DriverRouteState.Success -> {
                        Text(
                            text = " Route Id = ${routeValue.route.id} " +
                                    "\n Driver Id = $driverId  " +
                                    "\n Route Type = ${routeValue.route.type}  " +
                                    "\n Route Name = ${routeValue.route.name}"
                        )
                    }
                    is DriverRouteState.Error -> {
                        Text(text = routeValue.message, modifier = Modifier.align(Alignment.Center))
                    }
                }
            }

        }


    }
}
