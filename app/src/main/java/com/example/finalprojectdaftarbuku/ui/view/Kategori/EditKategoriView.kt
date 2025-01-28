package com.example.finalprojectdaftarbuku.ui.view.Kategori

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.example.finalprojectdaftarbuku.ui.viewmodel.KategoriVM.EditKategoriViewModel
import com.example.finalprojectdaftarbuku.ui.viewmodel.PenyediaViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun EditKategoriView(
    NavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    onNavigate: () -> Unit,
    viewModel: EditKategoriViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val coroutineScope = rememberCoroutineScope()

    Scaffold (
        topBar = {
            TopBarApp(
                judul = "Edit Data Kategori",
                onBack = NavigateBack,
                showBackButton = true
            )
        }
    ){innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(R.color.creme2))
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            FormBodyKtg(
                kategoriUiState = viewModel.UpdateKtgUiState,
                onKategoriChange = viewModel::updateInsertKtgState,
                onSaveClick = {
                    coroutineScope.launch {
                        viewModel.updateKtg()
                        delay(600)
                        withContext(Dispatchers.Main){
                            onNavigate()
                        }
                    }
                    NavigateBack()
                }
            )
        }
    }
}