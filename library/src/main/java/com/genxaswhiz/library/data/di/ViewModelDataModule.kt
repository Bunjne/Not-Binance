package com.genxaswhiz.library.data.di

import com.genxaswhiz.library.data.view_model.CoinDescriptionViewModel
import com.genxaswhiz.library.data.view_model.CoinViewModel
import com.genxaswhiz.library.data.view_model.NewsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ViewModelDataModule {
    val viewModelModules = module {
        viewModel { CoinViewModel(get()) }
        viewModel { NewsViewModel(get()) }
        viewModel { CoinDescriptionViewModel(get()) }
    }
}