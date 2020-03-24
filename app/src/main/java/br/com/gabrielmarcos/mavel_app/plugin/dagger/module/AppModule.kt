package br.com.gabrielmarcos.mavel_app.plugin.dagger.module

import android.content.Context
import br.com.gabrielmarcos.mavel_app.application.MarvelApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun providesContext(application: MarvelApplication): Context = application.applicationContext
}