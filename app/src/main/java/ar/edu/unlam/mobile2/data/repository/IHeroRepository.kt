package ar.edu.unlam.mobile2.data.repository

import ar.edu.unlam.mobile2.domain.difficulty.Difficulty
import ar.edu.unlam.mobile2.domain.hero.DataHero

interface IHeroRepository {

    suspend fun getRandomPlayerDeck(size:Int): List<DataHero>
    suspend fun getAdversaryDeck(size:Int): List<DataHero>
    suspend fun getHero(heroId:Int):DataHero
    suspend fun getAllHero():List<DataHero>
}