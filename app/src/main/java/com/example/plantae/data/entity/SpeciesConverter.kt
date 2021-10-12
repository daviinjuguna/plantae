package com.example.plantae.data.entity

import androidx.room.TypeConverter
import com.example.plantae.data.entity.Species
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.Serializable

class SpeciesConverter : Serializable {
    @TypeConverter
    fun fromSpeciesList(species: List<Species?>?): String? {
        if (species == null) return null
        val gson = Gson()
        val type = object : TypeToken<List<Species?>?>() {}.type
        return gson.toJson(species, type)
    }

    @TypeConverter
    fun toSpeciesList(speciesString: String?): List<Species>? {
        if (speciesString == null) return null
        val gson = Gson()
        val type = object : TypeToken<List<Species?>?>() {}.type
        return gson.fromJson(speciesString, type)
    }
}