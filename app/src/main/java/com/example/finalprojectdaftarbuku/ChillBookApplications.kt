package com.example.finalprojectdaftarbuku

import android.app.Application
import com.example.finalprojectdaftarbuku.di.AppContainer
import com.example.finalprojectdaftarbuku.di.ChillBookContainer

class ChillBookApplications: Application() {
    lateinit var container: AppContainer
    override fun onCreate(){
        super.onCreate()
        container = ChillBookContainer()
    }
}