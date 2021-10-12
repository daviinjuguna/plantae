package com.example.plantae.repo

import com.example.plantae.data.entity.PlantList
import com.example.plantae.data.remote.BaseDataSource
import com.example.plantae.data.remote.PlantRemoteDataSource
import com.example.plantae.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PlantRepository
@Inject constructor(
    private val plantRemoteDataSource: PlantRemoteDataSource,
) : BaseDataSource() {
    fun getPlants(): Flow<NetworkResult<PlantList>> {
        return flow { emit(safeApiCall { plantRemoteDataSource.getPlants() }) }.flowOn(Dispatchers.IO)
    }
}