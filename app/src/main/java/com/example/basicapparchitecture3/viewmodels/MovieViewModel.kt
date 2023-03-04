package com.example.basicapparchitecture3.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.base.Result
import com.example.domain.intractors.GetMovieList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getMovieList : GetMovieList
) : ViewModel() {
    private val _movieListFlow = MutableStateFlow(MovieViewModelState(emptyList()))
    val movieListFlow : StateFlow<MovieViewModelState>
        get() = _movieListFlow

    init {
        getMovies()
    }

    private fun getMovies() {
        viewModelScope.launch {
            val res = getMovieList.executeSync(Unit)
            _movieListFlow.update {
                when (res) {
                    is Result.Success ->  it.copy(movieInfoList = res.data)
                    else -> it
                }
            }
        }
    }

}