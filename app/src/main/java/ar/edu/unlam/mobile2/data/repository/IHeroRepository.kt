package ar.edu.unlam.mobile2.data.repository

import ar.edu.unlam.mobile2.domain.difficulty.Difficulty
import ar.edu.unlam.mobile2.domain.hero.DataHero

interface IHeroRepository {
    fun getPlayerDeck(idDeck: Int): List<DataHero>
    fun getAdversaryDeck(difficulty: Difficulty): List<DataHero>
    fun getRandomPlayerDeck(): List<DataHero>
    fun getAdversaryDeck(): List<DataHero>
    fun getHero(heroId:Int):DataHero
    fun getAllHero():List<DataHero>
}