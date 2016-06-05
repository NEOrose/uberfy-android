package br.com.neorose.uberfy.di.module

import android.app.Application
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.converter.gson.GsonConverterFactory

@Module
class RemoteRetrofitModule(private val application: Application) {
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
    }

    @Provides
    fun provideConverterFactory(): Converter.Factory {
        val gsonBuilder = GsonBuilder()
        //        gsonBuilder.registerTypeAdapter(Date.class, new DateSerializer());
        //        gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
        return GsonConverterFactory.create(gsonBuilder.create())
    }

/*    @Provides
    fun provideRetrofit(@RemoteUri remoteUri: String, okHttpClient: OkHttpClient, converterFactory: Converter.Factory): Retrofit {
        return Retrofit.Builder().baseUrl(remoteUri)
                .client(okHttpClient)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
    }*/

}