package com.example.johnz_gmchallenge.di

import com.example.johnz_gmchallenge.api.ItunesApi
import com.example.johnz_gmchallenge.utils.BASE_URL
import com.example.johnz_gmchallenge.utils.DateAdapter
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideMoshi() : Moshi = Moshi.Builder().add(DateAdapter).build()

    @Singleton
    @Provides
    fun provideArtistApi(moshi: Moshi): ItunesApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
        .create(ItunesApi::class.java)
}