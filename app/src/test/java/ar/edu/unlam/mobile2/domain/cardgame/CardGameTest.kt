package ar.edu.unlam.mobile2.domain.cardgame

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import ar.edu.unlam.mobile2.domain.hero.DataHero
import ar.edu.unlam.mobile2.domain.hero.Powerstats
import ar.edu.unlam.mobile2.domain.heroDuel.Stat
import com.google.common.truth.Truth.*
import io.mockk.junit4.MockKRule
import org.junit.Before
import org.junit.Rule

import org.junit.Test

class CardGameTest {

    /*
        JUnit 4 exposes a rule-based API to allow for some automation following the test lifecycle.
        MockK includes a rule which uses this to set up and tear down your mocks without needing to
        manually call MockKAnnotations.init(this).
     */
    @get:Rule
    val mockkRule = MockKRule(this)

    /*
        Esta regla es para que se ejecute cada línea de código de forma secuencial.
        Nos sirve en casos en donde se necesite el uso de livedata, flow, suspend functions, etc.
     */
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    /*
        @relaxedmockk
        se pueden mockear manualmente los métodos que necesitamos y el resto se genera
        automáticamente.

        @mockk
        debemos generar manualmente una respuesta para todos los métodos.
     */

    private val playerDeck = listOf(
        DataHero(
            powerstats = Powerstats(
                combat = "1",
                durability = "1",
                intelligence = "1",
                power = "1",
                speed = "1",
                strength = "1"
            ),
            id = "1"
        ),
        DataHero(
            powerstats = Powerstats(
                combat = "10",
                durability = "10",
                intelligence = "10",
                power = "10",
                speed = "10",
                strength = "10"
            ),
            id = "10"
        ),
        DataHero(
            powerstats = Powerstats(
                combat = "100",
                durability = "100",
                intelligence = "100",
                power = "100",
                speed = "100",
                strength = "100"
            ),
            id = "100"
        )
    )

    private val adversaryDeck = listOf(
        DataHero(
            powerstats = Powerstats(
                combat = "1",
                durability = "1",
                intelligence = "300",
                power = "1",
                speed = "1",
                strength = "1"
            ),
            id = "2"
        ),
        DataHero(
            powerstats = Powerstats(
                combat = "10",
                durability = "10",
                intelligence = "10",
                power = "10",
                speed = "10",
                strength = "10"
            ),
            id = "22"
        ),
        DataHero(
            powerstats = Powerstats(
                combat = "100",
                durability = "100",
                intelligence = "100",
                power = "100",
                speed = "100",
                strength = "100"
            ),
            id = "222"
        )
    )

    private lateinit var game:CardGame

    @Before
    fun setUp() {
        game = CardGame(
            playerDeck = playerDeck,
            adversaryDeck = adversaryDeck
        )
    }

    @Test
    fun `que el puntaje del jugador sea correcto cuando su carta gana una pelea sin el multiplicador x2`() {
        val expectedScore = playerDeck[2].powerstats.combat.toInt() -
                adversaryDeck[0].powerstats.combat.toInt()
        val useMultix2 = false

        game.playerPlayCard(id = 100,stat = Stat.COMBAT,useMultix2 = useMultix2)
        val playerScore:Int = game.playerScore.value

        assertThat(playerScore).isEqualTo(expectedScore)
        assertThat(game.adversaryScore.value).isEqualTo(0)
    }

    @Test
    fun `que el puntaje del jugador sea correcto cuando su carta gana una pelea con el multiplicador x2`() {
        val expectedPlayerScore = (playerDeck[2].powerstats.combat.toInt() * 2) -
                adversaryDeck[0].powerstats.combat.toInt()
        val expectedAdversaryScore = 0
        val useMultix2 = true
        val stat = Stat.COMBAT

        game.playerPlayCard(id = 100,stat = stat,useMultix2 = useMultix2)
        val playerScore:Int = game.playerScore.value
        val adversaryScore = game.adversaryScore.value

        assertThat(playerScore).isEqualTo(expectedPlayerScore)
        assertThat(adversaryScore).isEqualTo(expectedAdversaryScore)
    }

    @Test
    fun `que el puntaje del adversario sea correcto cuando su carta gana una pelea y el jugador no usa el multi x2`() {
        val expectedAdversaryScore = adversaryDeck[0].powerstats.intelligence.toInt() -
                playerDeck[0].powerstats.intelligence.toInt()
        val expectedPlayerScore = 0
        val stat = Stat.INTELLIGENCE
        val useMultix2 = false

        game.playerPlayCard(id = 1,stat = stat,useMultix2 = useMultix2)
        val playerScore:Int = game.playerScore.value
        val adversaryScore = game.adversaryScore.value

        assertThat(playerScore).isEqualTo(expectedPlayerScore)
        assertThat(adversaryScore).isEqualTo(expectedAdversaryScore)
    }

    @Test
    fun `que el puntaje del adversario sea correcto cuando su carta gana una pelea y el jugador usa el multi x2`() {
        val expectedAdversaryScore = adversaryDeck[0].powerstats.intelligence.toInt() -
                (playerDeck[0].powerstats.intelligence.toInt() * 2)
        val expectedPlayerScore = 0
        val stat = Stat.INTELLIGENCE
        val useMultix2 = true

        game.playerPlayCard(id = 1,stat = stat,useMultix2 = useMultix2)
        val playerScore = game.playerScore.value
        val adversaryScore = game.adversaryScore.value

        assertThat(playerScore).isEqualTo(expectedPlayerScore)
        assertThat(adversaryScore).isEqualTo(expectedAdversaryScore)
    }
}