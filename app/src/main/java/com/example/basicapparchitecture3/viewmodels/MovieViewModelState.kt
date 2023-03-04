package com.example.basicapparchitecture3.viewmodels

import com.example.domain.models.Movie
import javax.inject.Inject

data class MovieViewModelState @Inject constructor(
    val movieInfoList : List<Movie>?
)
