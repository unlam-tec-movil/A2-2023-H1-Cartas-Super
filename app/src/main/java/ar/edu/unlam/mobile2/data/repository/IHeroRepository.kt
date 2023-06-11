package ar.edu.unlam.mobile2.data.repository

import ar.edu.unlam.mobile2.domain.difficulty.Difficulty
import ar.edu.unlam.mobile2.domain.hero.DataHero

interface IHeroRepository {
    suspend fun getPlayerDeck(idDeck: Int): List<DataHero>
    suspend fun getAdversaryDeck(difficulty: Difficulty): List<DataHero>
    suspend fun getRandomPlayerDeck(): List<DataHero>
    suspend fun getAdversaryDeck(): List<DataHero>
    suspend fun getHero(heroId:Int):DataHero
    suspend fun getAllHero():List<DataHero>
}