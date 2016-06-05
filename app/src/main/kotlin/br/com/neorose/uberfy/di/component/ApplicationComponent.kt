package br.com.neorose.uberfy.di.component

import br.com.neorose.uberfy.UberfyApplication
import br.com.neorose.uberfy.di.module.ApplicationModule
import br.com.neorose.uberfy.di.module.UberModule
import br.com.neorose.uberfy.ui.activity.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class, UberModule::class))
interface ApplicationComponent {
    fun inject(application: UberfyApplication)

    fun inject(mainActivity: MainActivity)
}
