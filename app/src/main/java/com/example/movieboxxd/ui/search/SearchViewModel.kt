package com.example.movieboxxd.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieboxxd.movie.domain.models.Movie
import com.example.movieboxxd.movie.domain.repository.MovieRepository
import com.example.movieboxxd.utils.collectAndHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: MovieRepository
): ViewModel() {
    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _searchState = MutableStateFlow(SearchState())
    val searchState = _searchState.asStateFlow()

    init {
        viewModelScope.launch {
            _searchText
                .debounce(800)
                .collect { query ->
                    if (query.isEmpty()) {
                        _searchState.update {
                            it.copy(movies = emptyList(), isLoading = false, error = null)
                        }
                    } else {
                        searchMovie(query)
                    }
                }
        }
    }

    fun onSearchTextChange(query: String) {
        _searchText.value = query
    }

    private fun searchMovie(query: String) = viewModelScope.launch {
        repository.searchMovie(query).collectAndHandle(
            onError = { error ->
                _searchState.update {
                    it.copy(isLoading = false, error = error?.message)
                }
            },
            onLoading = {
                _searchState.update {
                    it.copy(isLoading = true, error = null)
                }
            }
        ) { movies ->
            _searchState.update {
                it.copy(isLoading = false, error = null, movies = movies)
            }
        }
    }
}

data class SearchState(
    val movies: List<Movie> = emptyList(),
    val error: String? = null,
    val isLoading: Boolean = false
)