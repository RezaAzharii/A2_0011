package com.example.finalprojectdaftarbuku.ui.viewmodel

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.finalprojectdaftarbuku.ui.navigation.PengelolaHalaman

@Composable
fun ChillBookApp (
    modifier: Modifier = Modifier
){
    Scaffold (){
        Surface (
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ){
            PengelolaHalaman()
        }
    }
}