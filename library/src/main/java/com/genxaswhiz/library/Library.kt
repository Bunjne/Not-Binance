package com.genxaswhiz.library

import android.content.Context
import com.genxaswhiz.library.data.di.RemoteDataModule
import com.genxaswhiz.library.data.di.RepositoryDataModule
import com.genxaswhiz.library.data.di.ViewModelDataModule
import com.github.kittinunf.fuel.core.FuelManager
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

object Library {
    fun init(context: Context, baseUrl: String) {
        with(FuelManager.instance) {
            basePath = baseUrl
//            baseHeaders = mapOf(
//                "Content-Type" to "application/json"
//            )
        }

        startKoin {
            androidContext(context)
            modules(RemoteDataModule.remoteModules, RepositoryDataModule.repositoryModules, ViewModelDataModule.viewModelModules)
            androidLogger()
        }
    }
}