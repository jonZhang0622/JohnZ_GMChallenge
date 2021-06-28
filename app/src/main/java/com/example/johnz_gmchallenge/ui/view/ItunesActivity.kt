package com.example.johnz_gmchallenge.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.johnz_gmchallenge.ui.adapter.ItunesAdapter
import com.example.johnz_gmchallenge.R
import com.example.johnz_gmchallenge.databinding.ItunesActivityBinding
import com.example.johnz_gmchallenge.ui.viewmodel.ItunesViewModel
import com.example.johnz_gmchallenge.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ItunesActivity : AppCompatActivity() {

    private val itunesViewModel: ItunesViewModel by viewModels()
    private val itunesAdapter: ItunesAdapter by lazy { ItunesAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ItunesActivityBinding>(
            this, R.layout.itunes_activity
        ).apply {
            lifecycleOwner = this@ItunesActivity
            viewModel = itunesViewModel
            adapter = itunesAdapter
        }

        itunesViewModel.tracks.observe(this) { albums ->
           if (albums is Resource.Success) itunesAdapter.setList(albums.data)
        }
    }
}