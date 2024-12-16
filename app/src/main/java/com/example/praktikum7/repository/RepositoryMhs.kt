package com.example.praktikum7.repository

import com.example.praktikum7.data.entity.Mahasiswa

interface RepositoryMhs {

    suspend fun insertMhs(mahasiswa: Mahasiswa)
}