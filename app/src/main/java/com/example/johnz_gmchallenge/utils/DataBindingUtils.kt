package com.example.johnz_gmchallenge.utils

import android.view.View
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.johnz_gmchallenge.model.Result
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.textview.MaterialTextView
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("adapter")
fun RecyclerView.setAdapter(adapter: RecyclerView.Adapter<*>) {
    this.adapter = adapter
}

@BindingAdapter("release_year")
fun MaterialTextView.releaseYear(date: Date) {
    val formattedDate = try {
        SimpleDateFormat(DATE_PATTERN, Locale.getDefault()).format(date)
    } catch (e: Exception) {
        null
    }
    text = formattedDate
}

@BindingAdapter("error_message")
fun <T> TextInputLayout.errorMessage(data: Resource<T>?) {
    if (data is Resource.Error) {
        error = data.msg
        isErrorEnabled = true
    }
}

@BindingAdapter("is_visible")
fun View.isVisible(visible: Boolean) {
    visibility = when (visible) {
        true -> View.VISIBLE
        false -> View.GONE
    }
}
