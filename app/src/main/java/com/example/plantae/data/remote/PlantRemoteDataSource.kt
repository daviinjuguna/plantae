package com.example.plantae.data.remote

import com.example.plantae.data.api.PlantService
import javax.inject.Inject

class PlantRemoteDataSource
@Inject constructor(
    private val plantService: PlantService
)  {
    suspend fun getPlants() =  plantService.getPlants()
}