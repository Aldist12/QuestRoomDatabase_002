package com.example.praktikum7.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.praktikum7.repository.RepositoryMhs
import kotlinx.coroutines.launch

class UpdateMhsViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryMhs: RepositoryMhs
) : ViewModel() {
    var updateUIState by mutableStateOf(MhsUIState())
        private set

    private val _nim: String = checkNotNull(savedStateHandle[DestinasiUpdate.NIM])

    init {
        viewModelScope.launch {
            updateUIState = repositoryMhs.getMhs(_nim)
                .filterNotNull()
                .first()
                .toUiStateMhs()
        }
    }
}
fun validateFields(): Boolean {
    val event = updateUIState.mahasiswaEvent
    val errorState = FormErrorState(
        nim = if (event.nim.isNotEmpty()) null else "NIM tidak boleh kosong",
        nama = if (event.nama.isNotEmpty()) null else "Nama tidak boleh kosong",
        jenisKelamin = if (event.jenisKelamin.isNotEmpty()) null else "Jenis Kelamin tidak boleh kosong",
        alamat = if (event.alamat.isNotEmpty()) null else "Alamat tidak boleh kosong",
        kelas = if (event.kelas.isNotEmpty()) null else "Kelas tidak boleh kosong",
        angkatan = if (event.angkatan.isNotEmpty()) null else "Angkatan tidak boleh kosong"
    )
    updateUIState = updateUIState.copy(isEntryValid = errorState)
    return errorState.isValid()
}

