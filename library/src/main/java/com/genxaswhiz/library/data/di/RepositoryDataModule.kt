package com.genxaswhiz.library.data.di

import com.genxaswhiz.library.data.repository.impl.CoinRepositoryImpl
import org.koin.dsl.module

object RepositoryDataModule {
    val repositoryModules = module {
        factory { CoinRepositoryImpl(get()) }
    }
}