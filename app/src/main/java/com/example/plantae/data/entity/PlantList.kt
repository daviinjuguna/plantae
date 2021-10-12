package com.example.plantae.data.entity

import com.google.gson.annotations.SerializedName

data class PlantList(
    @SerializedName("plants") val plants : List<Plants>
)