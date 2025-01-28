package com.example.finalprojectdaftarbuku.ui.view.Buku

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finalprojectdaftarbuku.R
import com.example.finalprojectdaftarbuku.ui.customwidget.TopBarApp
import com.example.finalprojectdaftarbuku.ui.viewmodel.BukuVM.EditBukuViewModel
import com.example.finalprojectdaftarbuku.ui.viewmodel.PenyediaViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun EditBukuView(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    onNavigate: () -> Unit,
    viewModel: EditBukuViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val coroutineScope = rememberCoroutineScope()
    Scaffold (
        topBar = {
            TopBarApp(
                judul = "Masukan Data Kategori",
                onBack = onBack,
                showBackButton = true
            )
        }
    ){innerpadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(R.color.creme2))
                .padding(innerpadding)
                .verticalScroll(rememberScrollState())
        ) {
            EntryBodyBuku(
                bukuUiState = viewModel.updateBukuUiState,
                onBukuValueChange = viewModel::updateInsertBkuState,
                kategoriList = viewModel.kategoriList,
                penerbitList = viewModel.penerbitList,
                penulisList = viewModel.penulisList,
                onSaveClick ={
                    coroutineScope.launch {
                        viewModel.updateBku()
                        delay(600)
                        withContext(Dispatchers.Main){
                            onNavigate()
                        }
                    }
                }
            )
        }
    }
}