package com.example.praktikum7.repository

import com.example.praktikum7.data.dao.MahasiswaDao
import com.example.praktikum7.data.entity.Mahasiswa
import kotlinx.coroutines.flow.Flow

class LocalRepositoryMhs(
    private val mahasiswaDao: MahasiswaDao
) : RepositoryMhs {

    override suspend fun insertMhs(mahasiswa: Mahasiswa) {
        mahasiswaDao.insertMahasiswa(mahasiswa)
    }

    override suspend fun deleteMhs(mahasiswa: Mahasiswa) {
        mahasiswaDao.deleteMahasiswa(mahasiswa)
    }
    override fun getAllMhs(): Flow<List<Mahasiswa>> =
        mahasiswaDao.getAllMahasiswa()
}