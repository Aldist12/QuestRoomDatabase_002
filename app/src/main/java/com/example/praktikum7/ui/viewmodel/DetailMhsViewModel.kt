package com.example.praktikum7.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.praktikum7.repository.RepositoryMhs
import kotlinx.coroutines.flow.StateFlow

class DetailMhsViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryMhs: RepositoryMhs,
) : ViewModel() {
    private val _nim: String = checkNotNull(savedStateHandle[DestinasiDetail.NIM])
}
val detailUiEvent: StateFlow<DetailUiState> = RepositoryMhs.getMhs(_nim)
    .filterNotNull()
    .map {
        DetailUiState(
            detailUiEvent = it.toDetailUiEvent(),
            isLoading = false,
        )
    }
    .onStart {
        emit(DetailUiState(isLoading = true))
        delay(600)
    }
    .catch {
        emit(
            DetailUiState(
                isLoading = false,
                isError = true,
                errorMessage = it.message ?: "Terjadi Kesalahan",
            )
        )
    }
    .stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(2000),
        initialValue = DetailUiState(
            isLoading = true,
        )
    )
fun deleteMhs() {
    detailUiEvent.value.detailUiEvent.toMahasiswaEntity().let {
        viewModelScope.launch {
            RepositoryMhs.deleteMhs(it)
        }
    }
}
data class DetailUiState(
    val detailUiEvent: MahasiswaEvent = MahasiswaEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
) {
    val isUiEventEmpty: Boolean
        get() = detailUiEvent == MahasiswaEvent()

    val isUiEventNotEmpty: Boolean
        get() = detailUiEvent != MahasiswaEvent()
}


