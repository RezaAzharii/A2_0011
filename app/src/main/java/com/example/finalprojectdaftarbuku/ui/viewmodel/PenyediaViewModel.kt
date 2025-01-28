package com.example.finalprojectdaftarbuku.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.finalprojectdaftarbuku.ChillBookApplications
import com.example.finalprojectdaftarbuku.ui.viewmodel.BukuVM.DetailBukuViewModel
import com.example.finalprojectdaftarbuku.ui.viewmodel.BukuVM.EditBukuViewModel
import com.example.finalprojectdaftarbuku.ui.viewmodel.BukuVM.HomeViewModel
import com.example.finalprojectdaftarbuku.ui.viewmodel.BukuVM.StatusBukuViewModel
import com.example.finalprojectdaftarbuku.ui.viewmodel.BukuVM.TambahBukuViewModel
import com.example.finalprojectdaftarbuku.ui.viewmodel.KategoriVM.DaftarKategoriViewModel
import com.example.finalprojectdaftarbuku.ui.viewmodel.KategoriVM.DetailKategoriViewModel
import com.example.finalprojectdaftarbuku.ui.viewmodel.KategoriVM.EditKategoriViewModel
import com.example.finalprojectdaftarbuku.ui.viewmodel.KategoriVM.TambahKategoriViewModel
import com.example.finalprojectdaftarbuku.ui.viewmodel.PenerbitVM.DaftarPenerbitViewModel
import com.example.finalprojectdaftarbuku.ui.viewmodel.PenerbitVM.DetailPenerbitViewModel
import com.example.finalprojectdaftarbuku.ui.viewmodel.PenerbitVM.EditPenerbitViewModel
import com.example.finalprojectdaftarbuku.ui.viewmodel.PenerbitVM.TambahPenerbitViewModel
import com.example.finalprojectdaftarbuku.ui.viewmodel.PenulsiVM.DaftarPenulisViewModel
import com.example.finalprojectdaftarbuku.ui.viewmodel.PenulsiVM.DetailPenulisViewModel
import com.example.finalprojectdaftarbuku.ui.viewmodel.PenulsiVM.EditPenulisViewModel
import com.example.finalprojectdaftarbuku.ui.viewmodel.PenulsiVM.TambahPenulisViewModel


object PenyediaViewModel{
    val Factory = viewModelFactory {
        //Buku
        initializer { HomeViewModel(aplikasiBuku().container.bukuRepository) }
        initializer { StatusBukuViewModel(aplikasiBuku().container.bukuRepository) }
        initializer {
            TambahBukuViewModel(
                aplikasiBuku().container.bukuRepository,
                aplikasiBuku().container.kategoriRepository,
                aplikasiBuku().container.penerbitRepository,
                aplikasiBuku().container.penulisRepository
            )
        }
        initializer {
            DetailBukuViewModel(
                createSavedStateHandle(),
                aplikasiBuku().container.bukuRepository,
                aplikasiBuku().container.kategoriRepository,
                aplikasiBuku().container.penerbitRepository,
                aplikasiBuku().container.penulisRepository
            )
        }
        initializer {
            EditBukuViewModel(
                createSavedStateHandle(),
                aplikasiBuku().container.bukuRepository,
                aplikasiBuku().container.kategoriRepository,
                aplikasiBuku().container.penerbitRepository,
                aplikasiBuku().container.penulisRepository
            )
        }

        //Kategori
        initializer { DaftarKategoriViewModel(aplikasiBuku().container.kategoriRepository) }
        initializer { TambahKategoriViewModel(aplikasiBuku().container.kategoriRepository) }
        initializer { DetailKategoriViewModel(
            createSavedStateHandle()
            ,aplikasiBuku().container.kategoriRepository
            )
        }
        initializer {
            EditKategoriViewModel(
                createSavedStateHandle(),
                aplikasiBuku().container.kategoriRepository
            )
        }

        //Penerbit
        initializer { DaftarPenerbitViewModel(aplikasiBuku().container.penerbitRepository) }
        initializer { TambahPenerbitViewModel(aplikasiBuku().container.penerbitRepository) }
        initializer {
            DetailPenerbitViewModel(
                createSavedStateHandle(),
                aplikasiBuku().container.penerbitRepository
            )
        }
        initializer {
            EditPenerbitViewModel(
                createSavedStateHandle(),
                aplikasiBuku().container.penerbitRepository
            )
        }

        //Penulis
        initializer { TambahPenulisViewModel(aplikasiBuku().container.penulisRepository) }
    }
}

fun CreationExtras.aplikasiBuku(): ChillBookApplications =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as ChillBookApplications)