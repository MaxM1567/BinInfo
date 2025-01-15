package com.example.bininfo.di

import android.app.Application
import androidx.room.Room
import com.example.bininfo.database.RequestDatabase
import com.example.bininfo.network.BinApi
import com.example.bininfo.repository.BinRepository
import com.example.bininfo.repository.RequestRepository
import com.example.bininfo.viewmodel.BinViewModel
import com.example.bininfo.viewmodel.RequestViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Модуль для предоставления Retrofit
val networkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://lookup.binlist.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single { get<Retrofit>().create(BinApi::class.java) }
}

// Модуль для предоставления Room DB
val roomModule = module {
    single {
        Room.databaseBuilder(
            get<Application>(), // Получение Application из Koin
            RequestDatabase::class.java,
            "request_db"
        ).build()
    }

    single { get<RequestDatabase>().getRequestDao() } // Предоставляем DAO
}

// Модуль для Repository
val repositoryBinModule = module {
    single { BinRepository(get()) }
}

val repositoryRequestModule = module {
    single { RequestRepository(get()) }
}

// Модуль для ViewModel
val binViewModelModule = module {
    viewModel { BinViewModel(get()) }
}

val requestViewModelModule = module {
    viewModel { RequestViewModel(get(), get()) } // Передаём Application и Repository
}