package ar.edu.unlam.mobile2.data.network

import ar.edu.unlam.mobile2.domain.hero.DataHero
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HeroService @Inject constructor(private val api:IHeroApiClient){

    suspend fun getHero(id:Int): DataHero {
        return withContext(Dispatchers.IO) {
            val response = api.getHeroResponse(id.toString())
            response.body() ?: DataHero()
        }
    }
}