package com.example.praktikum7.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.app.NotificationCompat.MessagingStyle.Message
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.praktikum7.data.entity.Mahasiswa
import com.example.praktikum7.repository.LocalRepositoryMhs
import com.example.praktikum7.repository.RepositoryMhs
import kotlinx.coroutines.launch

class MahasiswaViewModel(private val RepositoryMhs: RepositoryMhs) : ViewModel(){

    var uiState by mutableStateOf(MhsUIState())

    //Memperbarui state berdasarkan input pengguna
    fun updateState(mahasiswaEvent: MahasiswaEvent){
        uiState = uiState.copy(
            mahasiswaEvent = mahasiswaEvent,
        )
    }

    //Validasi data input pengguna
    private fun validateField(): Boolean{
        val event = uiState.mahasiswaEvent
            val errorState = FormErrorState(
                nim = if (event.nim.isNotEmpty()) null else "Nim tidak boleh kosong",
                nama = if (event.nama.isNotEmpty()) null else "Nama tidak boleh kosong",
                jenisKelamin = if (event.jenisKelamin.isNotEmpty()) null else "Jeniskelamin tidak boleh kosong",
                alamat = if (event.alamat.isNotEmpty()) null else "Alamat tidak boleh kosong",
                kelas = if (event.kelas.isNotEmpty()) null else "Kelas tidak boleh kosong",
                angkatan = if (event.angkatan.isNotEmpty()) null else "Angkatan tidak boleh kosong",

            )
        uiState = uiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }
    //Menyimpan data repository
    fun savedata(){
    val currentEvent = uiState.mahasiswaEvent
    if (validateField()) {
        viewModelScope.launch {
            try {
                RepositoryMhs.insertMhs(currentEvent.toMahasiswaEntity())
                uiState = uiState.copy(
                    snackBarMessage = "Data berhasil di simpan",
                    mahasiswaEvent = MahasiswaEvent(), //Reset input data
                    isEntryValid = FormErrorState(), // reset error state
                )
            } catch (e: Exception) {
                uiState = uiState.copy(
                    snackBarMessage = "Data gagal disimpan"
                )
            }
        }
    } else {
        uiState = uiState.copy(
            snackBarMessage = "Input tidak valid. periksa kembali data Anda."
        )
    }
    }
    //Reset pesan Snackbar setelah ditampilkan
    fun resetSnackBarMessage(){
        uiState = uiState.copy(snackBarMessage = null)
    }
}

data class MhsUIState(
    val mahasiswaEvent: MahasiswaEvent = MahasiswaEvent(),
    val isEntryValid: FormErrorState = FormErrorState(),
    val snackBarMessage: String? = null,

)

data class FormErrorState(
    val nim: String? = null,
    val nama: String? = null,
    val jenisKelamin: String? = null,
    val alamat: String? = null,
    val kelas: String? = null,
    val angkatan: String? = null,

){
    fun isValid(): Boolean{
        return nim == null && nama == null && jenisKelamin == null &&
                alamat == null && kelas == null && angkatan == null
    }
}



//data class Variabel yang menyimpan data input form
data class MahasiswaEvent(
    val nim : String ="",
    val nama : String ="",
    val jenisKelamin : String ="",
    val alamat : String ="",
    val kelas : String ="",
    val angkatan : String ="",
)

//Menyimpan input form ke dalam entity
fun MahasiswaEvent.toMahasiswaEntity(): Mahasiswa = Mahasiswa(
    nim = nim,
    nama = nama,
    jenisKelamin = jenisKelamin,
    alamat = alamat,
    kelas = kelas,
    angkatan = angkatan,
)