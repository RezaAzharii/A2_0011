package com.example.finalprojectdaftarbuku.ui.view.Buku

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finalprojectdaftarbuku.R
import com.example.finalprojectdaftarbuku.model.Buku
import com.example.finalprojectdaftarbuku.ui.customwidget.TopBarApp
import com.example.finalprojectdaftarbuku.ui.viewmodel.BukuVM.DetailBUiState
import com.example.finalprojectdaftarbuku.ui.viewmodel.BukuVM.DetailBukuViewModel
import com.example.finalprojectdaftarbuku.ui.viewmodel.PenyediaViewModel

@Composable
fun DetailBukuScreen(
    navigateBack: () -> Unit,
    navigateToItemUpdate: () -> Unit,
    navigateToKategori:() -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailBukuViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    Scaffold(
        topBar = {
            TopBarApp(
                judul = "Detail Buku",
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
                    contentDescription = "Edit Buku"
                )
            }
        }
    ) { innerPadding ->
        DetailBukuStatus(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(R.color.creme2))
                .padding(innerPadding),
            detailBukuUiState = viewModel.bkuDetailState,
            retryAction = { viewModel.getBukubyID() },
            onViewKategori = navigateToKategori
        )
    }
}

@Composable
fun DetailBukuStatus(
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onViewKategori: () -> Unit,
    detailBukuUiState: DetailBUiState
) {
    when (detailBukuUiState) {
        is DetailBUiState.Loading -> OnLoading(
            modifier = modifier.fillMaxSize()
        )

        is DetailBUiState.Success -> {
            if (detailBukuUiState.buku.idBuku == 0) {
                Box(
                    modifier = modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Data tidak ditemukan.")
                }
            } else {
                ItemDetailBuku(
                    buku = detailBukuUiState.buku,
                    kategori = detailBukuUiState.kategori,
                    penulis = detailBukuUiState.penulis,
                    penerbit = detailBukuUiState.penerbit,
                    modifier = modifier.fillMaxWidth(),
                    onViewKategori = onViewKategori
                )
            }
        }

        is DetailBUiState.Error -> OnError(
            retryAction,
            modifier = modifier.fillMaxSize()
        )
    }
}

@Composable
fun ItemDetailBuku(
    modifier: Modifier = Modifier,
    buku: Buku,
    kategori: String,
    penulis: String,
    penerbit: String,
    onViewKategori: () -> Unit
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
                text = buku.namaBuku,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = buku.deskripsiBuku,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = buku.tanggalTerbit,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = buku.statusBuku,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = kategori,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                IconButton(onClick = onViewKategori) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_remove_red_eye_24),
                        contentDescription = "Lihat Kategori",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
            Text(
                text = penulis,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = penerbit,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
