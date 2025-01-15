package com.example.bininfo

import android.app.Application
import com.example.bininfo.di.binViewModelModule
import com.example.bininfo.di.networkModule
import com.example.bininfo.di.repositoryBinModule
import com.example.bininfo.di.repositoryRequestModule
import com.example.bininfo.di.requestViewModelModule
import com.example.bininfo.di.roomModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        // Инициализация Koin
        startKoin {
            androidContext(this@MyApp)
            modules(
                listOf(
                    networkModule, roomModule,
                    repositoryBinModule, repositoryRequestModule,
                    binViewModelModule, requestViewModelModule
                )
            )
        }
    }
}