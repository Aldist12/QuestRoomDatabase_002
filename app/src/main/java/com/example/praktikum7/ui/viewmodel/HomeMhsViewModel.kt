package com.example.praktikum7.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.praktikum7.data.entity.Mahasiswa
import com.example.praktikum7.repository.RepositoryMhs
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.internal.NopCollector.emit

class HomeMhsViewModel(
    private val repositoryMhs: RepositoryMhs
) : ViewModel()
val homeUiState: StateFlow<HomeUiState> = RepositoryMhs.getAllMhs()
    .filterNotNull()
    .map {
        HomeUiState(
            listMhs = it.toList(),
            isLoading = false,
        )
    }
    .onStart {
        emit(HomeUiState(isLoading = true))
        delay(900)
    }
    .catch {
        emit(
            HomeUiState(
                isLoading = false,
                isError = true,
                errorMessage = it.message ?: "Terjadi kesalahan"
            )
        )
    }




