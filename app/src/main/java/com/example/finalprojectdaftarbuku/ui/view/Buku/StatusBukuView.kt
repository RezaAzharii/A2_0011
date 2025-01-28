package com.example.finalprojectdaftarbuku.ui.view.Buku

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finalprojectdaftarbuku.R
import com.example.finalprojectdaftarbuku.model.Buku
import com.example.finalprojectdaftarbuku.ui.customwidget.BottomBar
import com.example.finalprojectdaftarbuku.ui.customwidget.TopMainBar
import com.example.finalprojectdaftarbuku.ui.viewmodel.BukuVM.StatusBUiState
import com.example.finalprojectdaftarbuku.ui.viewmodel.BukuVM.StatusBukuViewModel
import com.example.finalprojectdaftarbuku.ui.viewmodel.PenyediaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatusScreen(
    navigateToHome: () -> Unit,
    navigateToDftrKategori: () -> Unit,
    navigateToDftrPenerbit: () -> Unit,
    navigateToDftrPenulis: () -> Unit,
    modifier: Modifier = Modifier,
    navigateDetailBku: (Int) -> Unit = {},
    navigateEditBku: (Int) -> Unit = {},
    viewModel: StatusBukuViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    Scaffold (
        topBar = {
            TopMainBar(
                judul = "Status",
                navigateToDftrHome = navigateToHome,
                navigateToDftrKategori = navigateToDftrKategori,
                navigateToDftrPenerbit = navigateToDftrPenerbit,
                navigateToDftrPenulis = navigateToDftrPenulis
            )
        },
        bottomBar = {
            BottomBar(
                initialSelectedItem = 2,
                navigateToDftrStatus = { },
                navigateToDftrHome = navigateToHome,
            )
        }
    ){innerPadding ->
        Column(
            modifier = Modifier
                .background(color = colorResource(R.color.creme2))
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = { viewModel.getPsBuku() },
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier
                        .width(190.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.green),
                        contentColor = Color.White
                    )
                ){
                    Text("Pesan")
                }
                Button(
                    onClick = { viewModel.getPhBuku() },
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier
                        .width(190.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.green),
                        contentColor = Color.White
                    )
                ){
                    Text("Habis")
                }
            }
            BookStatus (
                statUiState = viewModel.statUiState,
                retryAction = { viewModel.getPsBuku() },
                onDetailClick = navigateDetailBku,
                onEditClick = navigateEditBku,
                onDeleteClick = {
                    viewModel.deleteBuku(it.idBuku)
                    viewModel.getPsBuku()
                },
            )
        }
    }
}

@Composable
fun BookStatus(
    statUiState: StatusBUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (Int) -> Unit,
    onEditClick: (Int) -> Unit,
    onDeleteClick: (Buku) -> Unit
){
    when(statUiState){
        is StatusBUiState.Loading -> OnLoading(modifier = Modifier.fillMaxSize())

        is StatusBUiState.Success ->
            if (statUiState.buku.isEmpty()){
                return Box(
                    modifier = modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    Text("Tidak ada data Buku")
                }
            }else{
                BookLayout(
                    buku = statUiState.buku,
                    modifier = Modifier
                        .fillMaxSize(),
                    onDetailClick = {
                        onDetailClick(it.idBuku)
                    },
                    onEditClick = {
                        onEditClick(it.idBuku)
                    },
                    onDeleteClick = {
                        onDeleteClick(it)
                    }
                )
            }
        is StatusBUiState.Error -> OnError(retryAction, modifier = modifier.fillMaxSize())
    }
}