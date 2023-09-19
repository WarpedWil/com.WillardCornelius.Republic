package com.willard.data.dataSources.local

import androidx.room.RoomDatabase
import com.willard.data.dataSources.local.entities.DriversEntity
import com.willard.data.dataSources.local.entities.RoutesEntity
import com.willard.data.dataSources.local.daos.DriverDao
import com.willard.data.dataSources.local.daos.RouteDao

@androidx.room.Database(entities = [DriversEntity::class, RoutesEntity::class], version = 1)
abstract class Database : RoomDatabase() {

    abstract fun driverDao(): DriverDao

    abstract fun routesDao():RouteDao
}