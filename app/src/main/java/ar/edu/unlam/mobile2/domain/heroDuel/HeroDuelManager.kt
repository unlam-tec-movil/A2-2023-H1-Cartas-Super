package ar.edu.unlam.mobile2.domain.heroDuel

import ar.edu.unlam.mobile2.domain.hero.DataHero
import ar.edu.unlam.mobile2.domain.hero.Powerstats

class HeroDuelManager(
    private val playerDeck: MutableList<DataHero>,
    private val adversaryDeck: MutableList<DataHero>) {

    var playerPoints: Int = 0
    var adversaryPoints: Int = 0

    fun heroDuel(playerCardSelectedID: Int, statSelected: Stat, multiplier:Multiplier): Winner {
        val playerCard = playerDeck[playerCardSelectedID]
        val playerCardStat = selectStat(playerCard,statSelected)
        val playerStatWithMulti = multiplyStat(playerCardStat,multiplier)
        val adversaryCard = adversaryDeck[0]
        val adversaryCardStat = selectStat(adversaryCard,statSelected)

        return if( playerStatWithMulti > adversaryCardStat) {
            playerPoints += playerStatWithMulti
            adversaryDeck.removeAt(0)
            Winner.PLAYER
        } else {
            adversaryPoints += adversaryCardStat
            playerDeck.removeAt(playerCardSelectedID)
            Winner.ADVERSARY
        }
    }


    private fun selectStat(dataHero: DataHero, stat: Stat):Int {
        val powerStats: Powerstats = dataHero.powerstats
        return when(stat) {
            Stat.COMBAT -> powerStats.combat.toInt()
            Stat.DURABILITY -> powerStats.durability.toInt()
            Stat.INTELLIGENCE -> powerStats.intelligence.toInt()
            Stat.POWER -> powerStats.power.toInt()
            Stat.SPEED -> powerStats.speed.toInt()
            Stat.STRENGTH -> powerStats.strength.toInt()
        }
    }

    private fun multiplyStat(stat: Int, multiplier: Multiplier) : Int {
        return when(multiplier) {
            Multiplier.DEFAULT -> stat
            Multiplier.BY_TWO -> stat * 2
            Multiplier.BY_THREE -> stat * 3
        }
    }

    fun getGameWinner(): Winner {
        return if(playerPoints > adversaryPoints) Winner.PLAYER else Winner.ADVERSARY
    }
}