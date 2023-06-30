package ar.edu.unlam.mobile2.ui.utilities

import ar.edu.unlam.mobile2.domain.hero.DataHero

data class HeroState (
    val dataHero: DataHero = DataHero(),
    val isLoading:Boolean = false
    )