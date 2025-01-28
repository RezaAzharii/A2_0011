package com.example.finalprojectdaftarbuku.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.finalprojectdaftarbuku.ui.view.Buku.DetailBukuScreen
import com.example.finalprojectdaftarbuku.ui.view.Buku.EditBukuView
import com.example.finalprojectdaftarbuku.ui.view.Buku.EntryBukuScreen
import com.example.finalprojectdaftarbuku.ui.view.Buku.HomeScreen
import com.example.finalprojectdaftarbuku.ui.view.Buku.StatusScreen
import com.example.finalprojectdaftarbuku.ui.view.Kategori.DaftarKategori
import com.example.finalprojectdaftarbuku.ui.view.Kategori.DetailKtgScreen
import com.example.finalprojectdaftarbuku.ui.view.Kategori.EditKategoriView
import com.example.finalprojectdaftarbuku.ui.view.Kategori.TambahKategoriScreen
import com.example.finalprojectdaftarbuku.ui.view.Penerbit.DaftarPenerbit
import com.example.finalprojectdaftarbuku.ui.view.Penerbit.DetailPnbScreen
import com.example.finalprojectdaftarbuku.ui.view.Penerbit.EditPenerbitView
import com.example.finalprojectdaftarbuku.ui.view.Penerbit.TambahPenerbitScreen
import com.example.finalprojectdaftarbuku.ui.view.Penulis.DaftarPenulis
import com.example.finalprojectdaftarbuku.ui.view.Penulis.DetailPnsScreen
import com.example.finalprojectdaftarbuku.ui.view.Penulis.EditPenulisView
import com.example.finalprojectdaftarbuku.ui.view.Penulis.TambahPenulisScreen

@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController()
){
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = Modifier
    ) {
        //Buku
        composable(DestinasiHome.route) {
            HomeScreen(
                navigateToItemEntry = { navController.navigate(DestinsaiInsertBuku.route) },
                navigateToDftrStatus = { navController.navigate(DestinasiStatus.route) },
                navigateToDftrKategori = { navController.navigate(DestinasiDaftarKategori.route) },
                navigateToDftrPenerbit = { navController.navigate(DestinasiDaftarPenerbit.route) },
                navigateToDftrPenulis = { navController.navigate(DestinasiDaftarPenulis.route) },
                navigateDetailBku = { idBuku ->
                    navController.navigate("${DestinasiDetailBuku.route}/$idBuku")
                },
                navigateEditBku = { idBku ->
                    navController.navigate("${DestinasiUpdateBuku.route}/$idBku")
                }
            )
        }
        composable(DestinasiStatus.route){
            StatusScreen(
                navigateToHome = {navController.navigate(DestinasiHome.route)},
                navigateToDftrKategori = {navController.navigate(DestinasiDaftarKategori.route)},
                navigateToDftrPenerbit = {navController.navigate(DestinasiDaftarPenerbit.route)},
                navigateToDftrPenulis = {navController.navigate(DestinasiDaftarPenulis.route)},
                navigateDetailBku = {idBuku->
                    navController.navigate("${DestinasiDetailBuku.route}/$idBuku")
                },
                navigateEditBku = {idBku ->
                    navController.navigate("${DestinasiUpdateBuku.route}/$idBku")
                }
            )
        }
        composable(DestinsaiInsertBuku.route) {
            EntryBukuScreen(
                NavigateBack = {
                    navController.navigate(DestinasiHome.route) {
                        popUpTo(DestinasiHome.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(
            DestinasiDetailBuku.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiDetailBuku.IDB){
                    type = NavType.IntType
                }
            )
        ){
            val idb = it.arguments?.getInt(DestinasiUpdateBuku.IDB)
            idb?.let {
                DetailBukuScreen(
                    navigateToItemUpdate = {
                        navController.navigate("${DestinasiUpdateBuku.route}/$idb")
                    },
                    navigateBack = {
                        navController.navigate(DestinasiHome.route){
                            popUpTo(DestinasiHome.route){
                                inclusive = true
                            }
                        }
                    },
                    navigateToKategori = {
                        navController.navigate(DestinasiDaftarKategori.route)
                    }
                )
            }
        }
        composable(
            DestinasiUpdateBuku.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiUpdateBuku.IDB){
                    type = NavType.IntType
                }
            )
        ){
            val idb = it.arguments?.getInt(DestinasiUpdateBuku.IDB)
            idb?.let {
                EditBukuView(
                    onBack = {
                        navController.popBackStack()
                    },
                    onNavigate ={
                        navController.navigate(DestinasiHome.route)
                    }
                )
            }
        }

    }
}