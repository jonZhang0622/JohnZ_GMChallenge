package com.example.johnz_gmchallenge

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.johnz_gmchallenge.api.ItunesApi
import com.example.johnz_gmchallenge.model.Result
import com.example.johnz_gmchallenge.model.ResultResponse
import com.example.johnz_gmchallenge.repos.ItunesRepo
import com.example.johnz_gmchallenge.ui.viewmodel.ItunesViewModel
import com.example.johnz_gmchallenge.utils.Resource
import com.example.johnz_gmchallenge.utils.isSuccess
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response
import java.util.*

@ExperimentalCoroutinesApi
class ItunesViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    private val captor = mutableListOf<Resource<List<Result>>>()

    private val itunesApi = mockk<ItunesApi>()

    private val mockObserver: Observer<Resource<List<Result>>> = mockk(relaxed = true)

    private val repo = mockk<ItunesRepo>()

    private lateinit var result: Result

    @Before
    fun setup() {
        result = Result(
            artistName = "U2",
            primaryGenreName = "Rock",
            releaseDate = Date(),
            trackName = "track name",
            trackPrice = 12.99
        )
    }

    @Test
    fun `when calling getTracks with valid query, albums livedata should be updated with Success`() {
        coroutineScope.runBlockingTest {
            val responseDTO = ResultResponse(12, listOf(result))
            coEvery { itunesApi.getAlbums("U2") } returns Response.success(responseDTO)
            coEvery { repo.fetchTracks("U2") } returns Resource.Success(listOf(result))

            val itunesViewModel = ItunesViewModel(repo)
            itunesViewModel.tracks.observeForever(mockObserver)
            itunesViewModel.artistQuery = "U2"
            itunesViewModel.fetchTracks()

            verify(exactly = 2) { mockObserver.onChanged(capture(captor)) }
            assertEquals(Resource.Success(responseDTO.results), captor[1])
        }
    }

    @Test
    fun `when calling getTracks with empty query, albums livedata should be updated with Failure`() {

            val itunesViewModel = ItunesViewModel(repo)
            itunesViewModel.tracks.observeForever(mockObserver)
            itunesViewModel.artistQuery = ""
            itunesViewModel.fetchTracks()

            verify(exactly = 1) { mockObserver.onChanged(capture(captor)) }
            assertEquals(Resource.Error("Enter a query"), captor[0])
    }
}