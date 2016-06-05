package br.com.neorose.uberfy

import android.app.Application
import br.com.neorose.uberfy.di.component.ApplicationComponent
import br.com.neorose.uberfy.di.component.DaggerApplicationComponent
import br.com.neorose.uberfy.di.module.ApplicationModule
import br.com.neorose.uberfy.di.module.RemoteRetrofitModule

class UberfyApplication : Application() {

    companion object {
        //platformStatic allow access it from java code
        @JvmStatic lateinit var graph: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        graph = DaggerApplicationComponent.builder()
                    .applicationModule(ApplicationModule(this))
                    .remoteRetrofitModule(RemoteRetrofitModule(this))
                    .build()
        graph.inject(this)
    }
}