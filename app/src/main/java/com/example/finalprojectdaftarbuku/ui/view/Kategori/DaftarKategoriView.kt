package com.example.finalprojectdaftarbuku.ui.view.Kategori

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finalprojectdaftarbuku.R
import com.example.finalprojectdaftarbuku.model.Kategori
import com.example.finalprojectdaftarbuku.ui.customwidget.BottomBar
import com.example.finalprojectdaftarbuku.ui.customwidget.TopMainBar
import com.example.finalprojectdaftarbuku.ui.navigation.DestinasiStatus
import com.example.finalprojectdaftarbuku.ui.viewmodel.KategoriVM.DaftarKategoriUiState
import com.example.finalprojectdaftarbuku.ui.viewmodel.KategoriVM.DaftarKategoriViewModel
import com.example.finalprojectdaftarbuku.ui.viewmodel.PenyediaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DaftarKategori(
    navigateToHome: () -> Unit,
    navigateToDftrStatus: () -> Unit,
    navigateToDftrPenerbit: () -> Unit,
    navigateToDftrPenulis: () -> Unit,
    NavigateToEntryKategori: () -> Unit,
    NavigateEditClick: (Int) -> Unit = {},
    onDetailClick: (Int) -> Unit = {},
    modifier: Modifier = Modifier,
    viewModel: DaftarKategoriViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold (
        topBar = {
            TopMainBar(
                judul = "Categories",
                initialselected = 1,
                navigateToDftrHome = navigateToHome,
                navigateToDftrKategori = { },
                navigateToDftrPenerbit = navigateToDftrPenerbit,
                navigateToDftrPenulis = navigateToDftrPenulis
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = NavigateToEntryKategori,
                shape = MaterialTheme.shapes.medium,
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = Color.White,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Buku"
                )
            }
        },
        bottomBar = {
            BottomBar(
                initialSelectedItem = -1,
                navigateToDftrStatus = navigateToDftrStatus,
                navigateToDftrHome = navigateToHome
            )
        }
    ){innerPadding ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(R.color.creme2))
                .padding(innerPadding)
        ){
            KategoriStatus(
                kategoriUiState = viewModel.ktgUiState,
                retryAction = { viewModel.getKategori() },
                onDetailClick = onDetailClick,
                onEditClick = NavigateEditClick,
                onDeleteClick = {
                    viewModel.deleteKategori(it.idKategori)
                    viewModel.getKategori()
                }
            )
        }
    }
}

@Composable
fun KategoriStatus(
    kategoriUiState: DaftarKategoriUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (Int) -> Unit,
    onEditClick: (Int) -> Unit,
    onDeleteClick: (Kategori) -> Unit = {},
){
    when(kategoriUiState){
        is DaftarKategoriUiState.Loading -> OnLoading(modifier = Modifier.fillMaxSize())

        is DaftarKategoriUiState.Success ->
            if(kategoriUiState.kategori.isEmpty()){
                return Box(
                    modifier = modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    Text("Tidak ada Data Kategori")
                }
            }else{
                KategoriLayout(
                    kategori = kategoriUiState.kategori,
                    modifier = modifier.fillMaxWidth(),
                    onDetailClick = {
                        onDetailClick(it)
                    },
                    onEditClick = {
                        onEditClick(it)
                    },
                    onDeleteClick = {
                        onDeleteClick(it)
                    }
                )
            }
        is DaftarKategoriUiState.Error -> OnError(retryAction, modifier = modifier.fillMaxSize())
    }
}

@Composable
fun OnLoading(
    modifier: Modifier
){
    Image(
        modifier =  modifier.size(200.dp),
        painter = painterResource(R.drawable.ic_launcher_foreground),
        contentDescription = stringResource(R.string.loading)
    )
}

@Composable
fun OnError(
    retryAction: () -> Unit,
    modifier: Modifier
){
    Column (
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Image(
            painter = painterResource(id=R.drawable.ic_launcher_foreground),
            contentDescription = ""
        )
        Text(
            text = stringResource(R.string.loading_failed),
            modifier = Modifier.padding(16.dp)
        )
        Button(onClick = retryAction) {
            Text(stringResource(R.string.retry))
        }
    }
}

@Composable
fun KategoriLayout(
    kategori: List<Kategori>,
    modifier: Modifier,
    onEditClick: (Int) -> Unit = {},
    onDetailClick: (Int) -> Unit = {},
    onDeleteClick: (Kategori) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(kategori) { ktg ->
            KategoriItem(
                kategori = ktg,
                onDetailClick = { onDetailClick(ktg.idKategori) },
                onEditClick = { onEditClick(ktg.idKategori) },
                onDeleteClick = { onDeleteClick(ktg) }
            )
            Divider(modifier = Modifier.fillMaxWidth())
        }
    }
}

@Composable
fun KategoriItem(
    kategori: Kategori,
    onDetailClick: () -> Unit,
    onEditClick: () -> Unit,
    onDeleteClick: (Kategori) -> Unit
) {
    var deleteConfirmationRequired by rememberSaveable { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = kategori.namaKategori,
            style = MaterialTheme.typography.titleLarge
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onDetailClick) {
                Icon(
                    painter = painterResource(R.drawable.baseline_remove_red_eye_24),
                    contentDescription = "Detail"
                )
            }
            IconButton(onClick = onEditClick) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit"
                )
            }
            IconButton(onClick = { deleteConfirmationRequired = true }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete"
                )
            }
        }
    }
    if (deleteConfirmationRequired) {
        DeleteConfirmationDialog(
            onDeleteConfirm = {
                deleteConfirmationRequired = false
                onDeleteClick(kategori)
            },
            onDeleteCancel = {
                deleteConfirmationRequired = false
            }, modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
private fun DeleteConfirmationDialog(
    onDeleteConfirm: () -> Unit,
    onDeleteCancel: () -> Unit,
    modifier: Modifier = Modifier
){
    AlertDialog(onDismissRequest = {},
        title = { Text("Delete Data")},
        text = { Text("Apakah anda yakin ingin menghapus data?")},
        modifier = modifier,
        dismissButton = {
            TextButton(onClick = onDeleteCancel) {
                Text(text = "Cancel")
            }
        },
        confirmButton = {
            TextButton(onClick = onDeleteConfirm) {
                Text(text = "Yes")
            }
        }
    )
}