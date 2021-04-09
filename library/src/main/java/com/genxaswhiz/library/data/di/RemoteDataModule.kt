package com.genxaswhiz.library.data.di

import com.genxaswhiz.library.data.remote.impl.CoinApiImpl
import org.koin.dsl.module

object RemoteDataModule {
    val remoteModules = module {
        factory { CoinApiImpl() }
    }
}