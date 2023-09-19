package com.willard.data.dataSources.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.willard.data.dataSources.local.entities.RoutesEntity

@Dao
interface RouteDao {

    @Query("SELECT * FROM RoutesEntity WHERE id = :id")
    fun getDriverByRouteId(id: Int): RoutesEntity?

    @Query("SELECT * FROM RoutesEntity WHERE type = :routeType")
    fun getDriverByRouteType(routeType: String): List<RoutesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(routes: List<RoutesEntity>)
}