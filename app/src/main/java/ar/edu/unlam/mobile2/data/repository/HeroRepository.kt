package ar.edu.unlam.mobile2.data.repository

import ar.edu.unlam.mobile2.domain.difficulty.Difficulty
import ar.edu.unlam.mobile2.domain.hero.DataHero
import ar.edu.unlam.mobile2.domain.hero.Powerstats
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class HeroRepository : IHeroRepository {


    override suspend fun getPlayerDeck(idDeck: Int): List<DataHero> {
        return getRandomPlayerDeck()
    }

    override suspend fun getAdversaryDeck(difficulty: Difficulty): List<DataHero> {
        return getRandomPlayerDeck()
    }

    override suspend fun getAdversaryDeck(): List<DataHero> {
        return getRandomPlayerDeck()
    }

    override suspend fun getRandomPlayerDeck(): List<DataHero> {
        val list = mutableListOf<DataHero>()
        val job = coroutineScope {
            launch {
                for (i in 1..3) {
                    val randomId = (1..731).random()
                    val hero = getHero(randomId)
                    list.add(hero)
                }
            }
        }
        job.join()
        return list
    }

    override suspend fun getHero(heroId: Int): DataHero {
        var hero: DataHero? = null
        val call = HeroApiClient.apiService.getHero(heroId.toString())
        val job = coroutineScope {
            launch(Dispatchers.IO) {
                hero = call.execute().body()
            }
        }
        job.join()
        return hero ?: DataHero()
    }

    override suspend fun getAllHero(): List<DataHero> {
        val list = mutableListOf<DataHero>()
        val job = coroutineScope {
            launch {
                for (i in 1..731) {
                    val hero = getHero(i)
                    list.add(hero)
                }
            }
        }
        job.join()
        return list
    }

}