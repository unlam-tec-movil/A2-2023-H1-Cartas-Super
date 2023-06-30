package ar.edu.unlam.mobile2.di

import ar.edu.unlam.mobile2.data.network.IHeroApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit():Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://superheroapi.com/api/10160635333444116/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideHeroApiClient(retrofit: Retrofit):IHeroApiClient {
        return retrofit.create(IHeroApiClient::class.java)
    }
}