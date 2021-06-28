package com.example.johnz_gmchallenge.api

import com.example.johnz_gmchallenge.model.ResultResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ItunesApi {

    @GET("search")
    suspend fun getAlbums(
        @Query("term") artistName: String
    ) : Response<ResultResponse>
}