package com.example.movieboxxd.ui.person

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieboxxd.person.domain.models.Person
import com.example.movieboxxd.person.domain.repository.PersonRepository
import com.example.movieboxxd.utils.K
import com.example.movieboxxd.utils.collectAndHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonViewModel @Inject constructor(
    private val repository: PersonRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _personState = MutableStateFlow(PersonState())
    val personState = _personState.asStateFlow()

    val id: Int = savedStateHandle.get<Int>(K.PERSON_ID) ?: -1

    init {
        fetchPersonById()
    }

    private fun fetchPersonById() = viewModelScope.launch {
        if (id == -1) {
            _personState.update {
                it.copy(isLoading = false, error = "Person not found")
            }
        } else {
            repository.fetchPerson(id).collectAndHandle(
                onError = { error ->
                    _personState.update {
                        it.copy(isLoading = false, error = error?.message)
                    }
                },
                onLoading = {
                    _personState.update {
                        it.copy(isLoading = true, error = null)
                    }
                }
            ) { person ->
                _personState.update {
                    it.copy(isLoading = false, error = null, person = person)
                }
            }
        }
    }
}

data class PersonState(
    val person: Person? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)