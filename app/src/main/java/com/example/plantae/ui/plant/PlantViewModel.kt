package com.example.plantae.ui.plant

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plantae.data.entity.PlantList
import com.example.plantae.repo.PlantRepository
import com.example.plantae.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlantViewModel @Inject constructor(
    private val repository: PlantRepository
) : ViewModel() {
//    val plant:LiveData<Resource<List<Plant>>> = repository.getPlants()
    private val _response:MutableLiveData<NetworkResult<PlantList>> = MutableLiveData()
    val response:LiveData<NetworkResult<PlantList>> =_response

    fun fetchPlants() = viewModelScope.launch {
        repository.getPlants().collect { values ->
            _response.value = values }
    }
}