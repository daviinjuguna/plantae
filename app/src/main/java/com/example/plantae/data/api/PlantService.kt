package com.example.plantae.data.api

import com.example.plantae.data.entity.PlantList
import retrofit2.Response
import retrofit2.http.GET

interface PlantService {

    @GET("999bfb26-7102-4c11-8923-f6de74ea47c0")
    suspend fun getPlants():Response<PlantList>
}