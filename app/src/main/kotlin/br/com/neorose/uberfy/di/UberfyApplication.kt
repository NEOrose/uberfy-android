package br.com.neorose.uberfy

import android.app.Application
import br.com.neorose.uberfy.di.component.ApplicationComponent
import br.com.neorose.uberfy.di.component.DaggerApplicationComponent
import br.com.neorose.uberfy.di.module.ApplicationModule
import br.com.neorose.uberfy.di.module.UberModule
import com.uber.sdk.android.core.UberSdk
import com.uber.sdk.rides.client.SessionConfiguration
import javax.inject.Inject

class UberfyApplication : Application() {

    companion object {
        //platformStatic allow access it from java code
        @JvmStatic lateinit var graph: ApplicationComponent
    }

    @Inject lateinit var sessionConfiguration: SessionConfiguration

    override fun onCreate() {
        super.onCreate()
        graph = DaggerApplicationComponent.builder()
                    .applicationModule(ApplicationModule(this))
                    .uberModule(UberModule())
                    .build()
        graph.inject(this)

        UberSdk.initialize(sessionConfiguration)
    }
}