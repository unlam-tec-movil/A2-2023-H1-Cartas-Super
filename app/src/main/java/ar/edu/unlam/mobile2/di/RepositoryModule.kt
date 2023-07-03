package ar.edu.unlam.mobile2.di

import ar.edu.unlam.mobile2.data.database.dao.HeroDao
import ar.edu.unlam.mobile2.data.network.HeroService
import ar.edu.unlam.mobile2.data.repository.GameRepository
import ar.edu.unlam.mobile2.data.repository.HeroRepository
import ar.edu.unlam.mobile2.data.repository.HeroRepositoryManualTest
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
    fun provideHeroRepository(api:HeroService,db:HeroDao):IHeroRepository {
        return HeroRepository(api,db)
    }

    @Singleton
    @Provides
    fun provideGameRepository(repo:IHeroRepository):GameRepository {
        return GameRepository(repo)
    }
}