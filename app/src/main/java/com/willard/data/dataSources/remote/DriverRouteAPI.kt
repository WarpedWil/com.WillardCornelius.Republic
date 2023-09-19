package com.willard.data.dataSources.remote

import com.willard.data.dataSources.remote.models.DriverRouteResponse
import retrofit2.http.GET


interface DriverRouteAPI {

@GET("/data")
suspend fun getDriversData():DriverRouteResponse

}