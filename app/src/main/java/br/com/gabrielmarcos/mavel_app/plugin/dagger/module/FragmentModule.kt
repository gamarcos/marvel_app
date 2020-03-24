package br.com.gabrielmarcos.mavel_app.plugin.dagger.module

import br.com.gabrielmarcos.mavel_app.feature.search.view.CharacterFragment
import br.com.gabrielmarcos.mavel_app.feature.search.view.SearchFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeSearchFragment(): SearchFragment

    @ContributesAndroidInjector
    abstract fun contributeCharacterFragment(): CharacterFragment
}