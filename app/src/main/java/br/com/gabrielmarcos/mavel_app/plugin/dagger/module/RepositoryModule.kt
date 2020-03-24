package br.com.gabrielmarcos.mavel_app.plugin.dagger.module

import br.com.gabrielmarcos.mavel_app.feature.search.business.interactor.SearchRepository
import br.com.gabrielmarcos.mavel_app.plugin.repository.SearchRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
internal abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindSearchRepository(repositoryInjector: SearchRepositoryImpl): SearchRepository
}
