package com.willard.data.repositories


import com.willard.data.dataSources.local.daos.DriverDao
import com.willard.data.dataSources.local.daos.RouteDao
import com.willard.data.dataSources.local.entities.DriversEntity
import com.willard.data.dataSources.remote.DriverRouteAPI
import com.willard.data.dataSources.remote.models.toDriverEntity
import com.willard.data.dataSources.remote.models.toRoutesEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DriverRepository @Inject constructor(
    private val driverRouteAPI: DriverRouteAPI,
    private val driverDao: DriverDao,
    private val routeDao: RouteDao
) {


    // called initially
    suspend fun getDriversByFirstName(): Flow<List<DriversEntity>> {
        val driversByFirstNameFlow = driverDao.getDriversByFirstName()
        if (driversByFirstNameFlow.first().isEmpty())
            fetchFromApiAndSaveToDb()
        return driversByFirstNameFlow
    }

    // called when sort button is clicked
    suspend fun getDriversByLastName(): Flow<List<DriversEntity>> {
        val driversByLastNameFlow = driverDao.getDriversByLastName()
        if (driversByLastNameFlow.first().isEmpty())
            fetchFromApiAndSaveToDb()
        return driversByLastNameFlow

    }

    //called if db is empty  to fetch all the data from api to save in db and return them
    private suspend fun fetchFromApiAndSaveToDb() {
        driverDao
            .insertAll(driverRouteAPI.getDriversData().drivers.map { it.toDriverEntity() })
        routeDao
            .insertAll(driverRouteAPI.getDriversData().routes.map { it.toRoutesEntity() })

    }
}