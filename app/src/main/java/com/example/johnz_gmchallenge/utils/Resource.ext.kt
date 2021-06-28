package com.example.johnz_gmchallenge.utils

val <T>Resource<T>?.isLoading : Boolean
    get() = this is Resource.Loading

val <T>Resource<T>?.isSuccess : Boolean
    get() = this is Resource.Success

val <T>Resource<T>?.isError : Boolean
    get() = this is Resource.Error