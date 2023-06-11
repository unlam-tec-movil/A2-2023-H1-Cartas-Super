package ar.edu.unlam.mobile2.di

import ar.edu.unlam.mobile2.data.repository.HeroRepository
import ar.edu.unlam.mobile2.data.repository.HeroRepositoryTest
import ar.edu.unlam.mobile2.data.repository.IHeroRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideHeroRepository():IHeroRepository {
        //return HeroRepositoryTest()
        return HeroRepository()
    }
}