package ar.edu.unlam.mobile2.ui.utilities

import ar.edu.unlam.mobile2.domain.hero.DataHero

data class DeckState (
    val deck: List<DataHero> = listOf(),
    val isLoading: Boolean = false,
    val selectedCardIndex: Int = 0
)