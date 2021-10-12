package com.example.plantae.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName


data class Plants (

    @SerializedName("name") val name : String,
    @SerializedName("image") val image : String,
    @SerializedName("habit") val habit : String,
    @SerializedName("lifespan") val lifespan : String,
    @SerializedName("exposure") val exposure : String,
    @SerializedName("water") val water : String,
    @SerializedName("features") val features : String,
    @SerializedName("species") val species : List<Species>
)