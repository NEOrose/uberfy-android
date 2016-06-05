package br.com.neorose.uberfy.di.module

import br.com.neorose.uberfy.BuildConfig
import br.com.neorose.uberfy.di.annotations.UberClientId
import com.uber.sdk.core.auth.Scope
import com.uber.sdk.rides.client.SessionConfiguration
import dagger.Module
import dagger.Provides
import java.util.*

@Module
class UberModule {

    @Provides
    @UberClientId
    fun providesUberClientId() : String {
        return BuildConfig.UBER_CLIENT_ID
    }

    @Provides
    fun providesUberSessionConfiguration(@UberClientId uberClientId: String) : SessionConfiguration {
        return SessionConfiguration.Builder()
                .setClientId(uberClientId)
                .setScopes(Arrays.asList(Scope.PROFILE, Scope.RIDE_WIDGETS))
                .setEnvironment(SessionConfiguration.Environment.SANDBOX)
                .build()
    }
}