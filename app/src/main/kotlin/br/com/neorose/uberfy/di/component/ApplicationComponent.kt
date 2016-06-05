package br.com.neorose.uberfy.di.component

import br.com.neorose.uberfy.UberfyApplication
import br.com.neorose.uberfy.di.module.ApplicationModule
import br.com.neorose.uberfy.di.module.RemoteRetrofitModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class, RemoteRetrofitModule::class))
interface ApplicationComponent {
    fun inject(application: UberfyApplication)

}
