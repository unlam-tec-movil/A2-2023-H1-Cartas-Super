package ar.edu.unlam.mobile2.ui.utilities

import ar.edu.unlam.mobile2.domain.hero.DataHero

data class HeroListState (
    val heroList: List<DataHero> = listOf(),
    val isLoading: Boolean = false
)