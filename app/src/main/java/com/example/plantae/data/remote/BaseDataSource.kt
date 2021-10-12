package com.example.plantae.data.remote

import com.example.plantae.utils.NetworkResult
import retrofit2.Response
import timber.log.Timber

abstract class BaseDataSource {

    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): NetworkResult<T> {
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return NetworkResult.Success(body)
                }
            }
            return error("${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): NetworkResult<T> {
        Timber.d(message)
        return NetworkResult.Error("Network call has failed for a following reason: $message")
    }

}