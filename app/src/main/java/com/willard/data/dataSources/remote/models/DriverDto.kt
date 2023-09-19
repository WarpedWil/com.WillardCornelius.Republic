package com.willard.data.dataSources.remote.models

import com.willard.data.dataSources.local.entities.DriversEntity

data class DriverDto(
    val id: String,
    val name: String
)

fun DriverDto.toDriverEntity(): DriversEntity {

    val nameArray = name.split(" ")



    return DriversEntity(id, nameArray.getOrNull(0) ?: "", nameArray.getOrNull(1) ?: "")

}