package com.example.finalprojectdaftarbuku.ui.view.Kategori

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finalprojectdaftarbuku.R
import com.example.finalprojectdaftarbuku.model.Kategori
import com.example.finalprojectdaftarbuku.ui.customwidget.TopBarApp
import com.example.finalprojectdaftarbuku.ui.viewmodel.KategoriVM.DetailKategoriViewModel
import com.example.finalprojectdaftarbuku.ui.viewmodel.KategoriVM.DetailKtgUiState
import com.example.finalprojectdaftarbuku.ui.viewmodel.PenyediaViewModel

@Composable
fun DetailKtgScreen(
    navigateBack: () -> Unit,
    navigateToItemUpdate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailKategoriViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    Scaffold(
        topBar = {
            TopBarApp(
                judul = "Detail Kategori",
                onBack = navigateBack,
                showBackButton = true
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemUpdate,
                shape = MaterialTheme.shapes.medium,
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = Color.White,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Kontak"
                )
            }
        }
    ) { innerPadding ->
        DetailKtgStatus(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(R.color.creme2))
                .padding(innerPadding),
            detailKtgUiState = viewModel.kategoriDetailState,
            retryAction = { viewModel.getKategoriById() }
        )
    }
}

@Composable
fun DetailKtgStatus(
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    detailKtgUiState: DetailKtgUiState
) {
    when (detailKtgUiState) {
        is DetailKtgUiState.Loading -> OnLoading(modifier = modifier.fillMaxSize())

        is DetailKtgUiState.Success -> {
            if (detailKtgUiState.kategori.idKategori == 0) {
                Box(
                    modifier = modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Data tidak ditemukan.")
                }
            } else {
                ItemDetailKtg(
                    kategori = detailKtgUiState.kategori,
                    modifier = modifier.fillMaxWidth()
                )
            }
        }

        is DetailKtgUiState.Error -> OnError(retryAction, modifier = modifier.fillMaxSize())
    }
}

@Composable
fun ItemDetailKtg(
    modifier: Modifier = Modifier,
    kategori: Kategori
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(colorResource(R.color.crem))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = kategori.namaKategori,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = kategori.deskripsiKategori,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}