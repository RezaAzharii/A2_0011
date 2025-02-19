package com.example.finalprojectdaftarbuku

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.finalprojectdaftarbuku.ui.theme.FinalProjectDaftarBukuTheme
import com.example.finalprojectdaftarbuku.ui.viewmodel.ChillBookApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FinalProjectDaftarBukuTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ChillBookApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
