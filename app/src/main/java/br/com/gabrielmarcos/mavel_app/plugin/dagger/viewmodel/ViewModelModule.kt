package br.com.gabrielmarcos.mavel_app.plugin.dagger.viewmodel

import androidx.lifecycle.ViewModel
import br.com.gabrielmarcos.mavel_app.feature.search.gateway.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindSearchViewModel(viewModel: SearchViewModel): ViewModel
}
