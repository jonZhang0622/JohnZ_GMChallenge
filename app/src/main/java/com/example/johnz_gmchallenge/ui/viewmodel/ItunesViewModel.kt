package com.example.johnz_gmchallenge.ui.viewmodel

import androidx.lifecycle.*
import com.example.johnz_gmchallenge.repos.ItunesRepo
import com.example.johnz_gmchallenge.model.Result
import com.example.johnz_gmchallenge.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ItunesViewModel @Inject constructor(
    private val itunesRepo: ItunesRepo
) : ViewModel() {

    var artistQuery = ""

    private var _tracks = MutableLiveData<Resource<List<Result>>>()
    val tracks: LiveData<Resource<List<Result>>> = _tracks

    fun fetchTracks() {
        if (artistQuery.isEmpty()) {
            _tracks.postValue(Resource.Error("Enter a query"))
        } else {
            viewModelScope.launch(Dispatchers.IO) {
                _tracks.postValue(Resource.Loading)
                try {
                    val response = itunesRepo.fetchTracks(artistQuery)
                    _tracks.postValue(response)
                } catch (e: Exception) {
                    _tracks.postValue(Resource.Error(e.message.toString()))
                }
            }
        }
    }


}