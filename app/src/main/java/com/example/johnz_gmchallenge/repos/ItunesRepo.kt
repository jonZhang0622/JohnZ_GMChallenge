package com.example.johnz_gmchallenge.repos

import android.util.Log
import com.example.johnz_gmchallenge.api.ItunesApi
import com.example.johnz_gmchallenge.model.Result
import com.example.johnz_gmchallenge.utils.Resource
import javax.inject.Inject

class ItunesRepo @Inject constructor(private val itunesApi: ItunesApi) {

    suspend fun fetchTracks(artistName: String): Resource<List<Result>> {
        Log.d("ItunesRepo", artistName)
        return try {
            val response = itunesApi.getAlbums(artistName)
            Log.d("ItunesRepo", response.toString())
            if (response.isSuccessful) {
                val results = response.body()?.results?.sortedBy { it.releaseDate }
                if (results != null && results.isNotEmpty()) {
                    Resource.Success(results)
                } else {
                    Resource.Error("No results found for $artistName")
                }
            } else {
                Resource.Error("No results found for $artistName")
            }
        } catch (ex: Exception) {
            Log.d("ItunesRepo", ex.toString())
            Resource.Error("Something went wrong.")
        }
    }
}