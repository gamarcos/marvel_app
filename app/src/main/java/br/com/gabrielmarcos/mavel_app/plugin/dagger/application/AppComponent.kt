package br.com.gabrielmarcos.mavel_app.plugin.dagger.application

import android.app.Application
import br.com.gabrielmarcos.mavel_app.application.MarvelApplication
import br.com.gabrielmarcos.mavel_app.plugin.dagger.module.AppModule
import br.com.gabrielmarcos.mavel_app.plugin.dagger.module.FragmentModule
import br.com.gabrielmarcos.mavel_app.plugin.dagger.module.RepositoryModule
import br.com.gabrielmarcos.mavel_app.plugin.dagger.module.RetrofitModule
import br.com.gabrielmarcos.mavel_app.plugin.dagger.viewmodel.ViewModelFactoryModule
import br.com.gabrielmarcos.mavel_app.plugin.dagger.viewmodel.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        RetrofitModule::class,
        ViewModelFactoryModule::class,
        ViewModelModule::class,
        FragmentModule::class,
        RepositoryModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(application: MarvelApplication)
}