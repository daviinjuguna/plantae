package com.example.plantae.di

import com.example.plantae.data.api.PlantService
import com.example.plantae.data.remote.PlantRemoteDataSource
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl("https://run.mocky.io/v3/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun providePlantService(retrofit: Retrofit): PlantService =
        retrofit.create(PlantService::class.java)

    @Singleton
    @Provides
    fun providePlantRemoteDataSource(plantService: PlantService) =
        PlantRemoteDataSource(plantService)
}