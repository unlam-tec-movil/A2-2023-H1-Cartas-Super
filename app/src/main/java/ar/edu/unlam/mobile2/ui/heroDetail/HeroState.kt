package ar.edu.unlam.mobile2.ui.heroDetail

import ar.edu.unlam.mobile2.domain.hero.DataHero

data class HeroState (
    val dataHero: DataHero = DataHero(),
    val isLoading:Boolean = false
    )