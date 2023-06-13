package ar.edu.unlam.mobile2.ui.heroDuel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile2.data.repository.HeroRepositoryTest
import ar.edu.unlam.mobile2.data.repository.IHeroRepository
import ar.edu.unlam.mobile2.domain.heroDuel.HeroDuelManager
import ar.edu.unlam.mobile2.domain.heroDuel.Multiplier
import ar.edu.unlam.mobile2.domain.heroDuel.Stat
import ar.edu.unlam.mobile2.domain.heroDuel.Winner
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeroDuelViewModelImp @Inject constructor(private val repo: IHeroRepository) : ViewModel() {
    var playerDeckState by mutableStateOf(DeckState())
        private set //para que la variable no pueda ser seteada por fuera de la clase
    var adversaryDeckState by mutableStateOf(DeckState())
        private set
    var hideHeroDuelUI = false
        private set
    var showActionMenu by mutableStateOf(false)
        private set
    var selectedStat by mutableStateOf(Stat.DURABILITY)
        private set
    var useMultiplierX2 by mutableStateOf(false)
        private set
    private lateinit var game: HeroDuelManager
    var heroDuelWinner by mutableStateOf(Winner.PLAYER)
        private set
    var showSelectCardMenu by mutableStateOf(true)
        private set
    var showGameWinner by mutableStateOf(false)
        private set
    var gameWinner by mutableStateOf(Winner.PLAYER)
        private set

    private val DECK_SIZE = 3




    init {
        val playerDeckJob = viewModelScope.launch {
            playerDeckState = playerDeckState.copy(
                isLoading = true
            )
            playerDeckState = playerDeckState.copy(
                deck = repo.getRandomPlayerDeck(DECK_SIZE),
                isLoading = false
            )
        }
        val adversaryDeckJob = viewModelScope.launch {
            adversaryDeckState = adversaryDeckState.copy(
                isLoading = true
            )
            adversaryDeckState = adversaryDeckState.copy(
                deck = repo.getAdversaryDeck(DECK_SIZE),
                isLoading = false
            )
        }
        viewModelScope.launch {
            playerDeckJob.join()
            adversaryDeckJob.join()
            game = HeroDuelManager(playerDeckState.deck.toMutableList(),adversaryDeckState.deck.toMutableList())
        }

    }

    fun selectedPlayerCard(i: Int) {
        playerDeckState = playerDeckState.copy(
            selectedCardIndex = i
        )
    }

    fun hideHeroDuelUI() {
        hideHeroDuelUI = true
    }

    fun selectedStat(selectedStat: Stat) {
        this.selectedStat = selectedStat
    }

    fun getStatList(): List<Stat> {
        return listOf(
            Stat.DURABILITY,
            Stat.COMBAT,
            Stat.INTELLIGENCE,
            Stat.POWER,
            Stat.SPEED,
            Stat.STRENGTH
        )
    }

    fun useMultiplierX2(state: Boolean) {
        useMultiplierX2 = state
    }

    fun startHeroDuelTurn() {
        val playerDeckIsEmpty = playerDeckState.deck.isEmpty()
        val adversaryDeckIsEmpty = adversaryDeckState.deck.isEmpty()
        if( playerDeckIsEmpty or adversaryDeckIsEmpty ) {
            showGameWinner()
        } else {
            val playerCardSelected = playerDeckState.selectedCardIndex
            heroDuelWinner = game.heroDuel(
                playerCardSelectedID = playerCardSelected,
                statSelected = selectedStat,
                multiplier = if(useMultiplierX2) Multiplier.BY_TWO
                else Multiplier.DEFAULT
            )
            if (heroDuelWinner == Winner.PLAYER)
                onAdversaryCardDefeated()
            else
                onPlayerCardDefeated()
        }
    }

    private fun showGameWinner() {
        showGameWinner = true
        gameWinner = game.getGameWinner()
    }

    private fun onPlayerCardDefeated() {
        val index = playerDeckState.selectedCardIndex
        val updatedDeck = playerDeckState.deck.toMutableList()
        updatedDeck.removeAt(index)
        if(updatedDeck.isEmpty()) {
            showGameWinner()
        } else {
            playerDeckState = playerDeckState.copy(
                deck = updatedDeck,
                selectedCardIndex = 0
            )
        }
    }

    private fun onAdversaryCardDefeated() {
        val updatedDeck = adversaryDeckState.deck.toMutableList()
        updatedDeck.removeAt(adversaryDeckState.selectedCardIndex)
        if(updatedDeck.isEmpty()) {
            showGameWinner()
        } else {
            adversaryDeckState = adversaryDeckState.copy(
                deck = updatedDeck
            )
        }
    }

    fun showActionMenu(state: Boolean) {
        showActionMenu = state
    }

    fun showSelectCardMenu(state: Boolean) {
        showSelectCardMenu = state
    }


}