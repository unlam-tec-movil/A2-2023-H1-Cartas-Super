package ar.edu.unlam.mobile2.ui.heroDuel

import android.content.Intent
import android.graphics.drawable.shapes.Shape
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.unlam.mobile2.R
import ar.edu.unlam.mobile2.data.repository.HeroRepositoryTest
import ar.edu.unlam.mobile2.domain.hero.DataHero
import ar.edu.unlam.mobile2.domain.hero.HeroImage
import ar.edu.unlam.mobile2.domain.hero.Powerstats
import ar.edu.unlam.mobile2.domain.heroDuel.Winner
import ar.edu.unlam.mobile2.ui.NewGameActivity
import ar.edu.unlam.mobile2.ui.ui.theme.Mobile2_ScaffoldingTheme
import ar.edu.unlam.mobile2.ui.ui.theme.shaka_pow
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HeroDuelActivity : ComponentActivity() {
    val viewModel by viewModels<HeroDuelViewModelImp>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Mobile2_ScaffoldingTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HeroDuelFondo()
                    HeroDuelUI(
                        modifier = Modifier,
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}

@Composable
fun HeroDuelFondo() {
    Image(
        painter = painterResource(id = R.drawable.fondo_pantalla_pelea),
        contentDescription = "Pantalla NewGame",
        contentScale = ContentScale.FillBounds,
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
fun HeroDuelUI(modifier: Modifier = Modifier, viewModel: HeroDuelViewModelImp) {
    val offset = Offset(6.0f, 4.0f)
    if (viewModel.showGameWinner) {
        Text(
            text = "El ganador es " + if (viewModel.gameWinner == Winner.PLAYER) "el jugador" else "el adversario",
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp, 300.dp),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            fontFamily = shaka_pow,
            style = TextStyle(
                fontSize = 24.sp,
                shadow = Shadow(
                    color = Color.Black,
                    offset = offset,
                    blurRadius = 4f
                )
            )

        )
    } else {
        if (viewModel.showSelectCardMenu) {
            SelectCard(modifier = modifier, viewModel = viewModel)
        } else {
            viewModel.showActionMenu(true)
            HeroDuel(modifier = modifier, viewModel = viewModel)
        }
    }

}

@Composable
fun HeroDuel(modifier: Modifier = Modifier, viewModel: HeroDuelViewModelImp) {
    val playerCard = viewModel.playerDeckState.deck[viewModel.playerDeckState.selectedCardIndex]
    val adversaryCard =
        viewModel.adversaryDeckState.deck[viewModel.adversaryDeckState.selectedCardIndex]

    Column(modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {

        HeroCard(modifier = modifier, hero = adversaryCard)

        if (viewModel.showActionMenu) {
            ActionMenu(modifier = modifier.weight(2.3f), viewModel = viewModel)
        } else {
            DuelResult(modifier = modifier.weight(2.3f), viewModel = viewModel)
        }
        HeroCard(modifier = modifier, hero = playerCard)

    }
}

@Composable
fun DuelResult(modifier: Modifier = Modifier, viewModel: HeroDuelViewModelImp) {
    val offset = Offset(6.0f, 4.0f)
    val playerWon = viewModel.heroDuelWinner == Winner.PLAYER
    val mensaje = if (playerWon) {
        //"¡Ganaste esta pelea y conservarás tu carta!"
        "Ganaste esta pelea"
    } else {
        "Perdiste esta pelea"
        //"Perdiste esta pelea y se envió tu carta al cementerio."
    }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp, 0.dp, 16.dp, 0.dp)
    ) {
        Text(
            modifier = modifier, fontWeight = FontWeight.Bold, text = mensaje, style = TextStyle(
                fontSize = 17.sp,
                shadow = Shadow(
                    color = Color.Black,
                    offset = offset,
                    blurRadius = 4f
                )
            )
        )
        Button(
            modifier = modifier
                .width(70.dp)
                .height(160.dp),
            onClick = {
                if (playerWon)
                    viewModel.showActionMenu(true)
                else {
                    viewModel.showActionMenu(false)
                    viewModel.showSelectCardMenu(true)
                }
            }
        ) {
            Text(modifier = modifier, textAlign = TextAlign.Center, text = "Continuar")
        }
    }

}


@Composable
fun ActionMenu(modifier: Modifier = Modifier, viewModel: HeroDuelViewModelImp) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp, 0.dp, 16.dp, 0.dp), horizontalArrangement = Arrangement.SpaceBetween
    ) {
        SelectStat(viewModel = viewModel)
        SelectMultiplier(viewModel = viewModel)
        Button(
            colors = ButtonDefaults.buttonColors(Color.Red),
            shape = ButtonDefaults.outlinedShape,
            onClick = {
                viewModel.showActionMenu(false)
                viewModel.startHeroDuelTurn()
            }
        ) {
            Text(
                text = "¡Pelear!", fontWeight = FontWeight.Bold, style = TextStyle(
                    fontSize = 18.sp
                )
            )
        }
    }
}

@Composable
fun SelectMultiplier(modifier: Modifier = Modifier, viewModel: HeroDuelViewModelImp) {
    val offset = Offset(6.0f, 4.0f)
    var checked by rememberSaveable {
        mutableStateOf(false)
    }
    Column(modifier = modifier) {
        Text(
            modifier = modifier, text = "Multi x2:", style = TextStyle(
                fontSize = 18.sp,
                shadow = Shadow(
                    color = Color.Black,
                    offset = offset,
                    blurRadius = 4f
                )
            )
        )
        Checkbox(
            checked = checked,
            onCheckedChange = {
                checked = !checked
                viewModel.useMultiplierX2(checked)
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectStat(modifier: Modifier = Modifier, viewModel: HeroDuelViewModelImp) {
    var expanded by rememberSaveable {
        mutableStateOf(false)
    }
    val statList = viewModel.getStatList()
    var selectedStat = viewModel.selectedStat

    Box(
        modifier = modifier
        //.padding(8.dp)
        //.width(160.dp)
        //.height(24.dp)
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            modifier = modifier.size(170.dp),
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            TextField(
                value = selectedStat.statName,
                onValueChange = { viewModel.selectedStat(selectedStat) },
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = modifier.menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                statList.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item.statName) },
                        onClick = {
                            viewModel.selectedStat(item)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}


@Composable
fun SelectCard(modifier: Modifier = Modifier, viewModel: HeroDuelViewModelImp) {
    val playerDeckIsLoading = viewModel.playerDeckState.isLoading
    val aIDeckIsLoading = viewModel.adversaryDeckState.isLoading
    if (playerDeckIsLoading or aIDeckIsLoading) {
        Box(modifier = modifier.fillMaxSize()) {
            CircularProgressIndicator(modifier = modifier.align(Alignment.Center))
        }
    } else {
        val heroCardSelected = viewModel.playerDeckState.selectedCardIndex
        val dataHero: DataHero = viewModel.playerDeckState.deck[heroCardSelected]
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                modifier = modifier,
                onClick = {
                    viewModel.showSelectCardMenu(false)
                }
            ) {
                Text(modifier = modifier, text = "Jugar Carta")
            }
            HeroCard(modifier = modifier, hero = dataHero)
            PlayerDeck(modifier = modifier, viewModel = viewModel)

        }
    }
}


@Composable
fun PlayerDeckNVM(
    modifier: Modifier = Modifier,
    playerDeck: List<DataHero>
) {
    LazyColumn(
        modifier = modifier,
        content = {
            items(playerDeck.size) { i ->
                HeroItem(
                    modifier = modifier.clickable { },
                    hero = playerDeck[i]
                )
            }
        })
}

@Composable
fun PlayerDeck(
    modifier: Modifier = Modifier,
    viewModel: HeroDuelViewModelImp
) {
    val playerDeck = viewModel.playerDeckState.deck
    LazyColumn(
        modifier = modifier,
        content = {
            items(playerDeck.size) { i ->
                HeroItem(
                    modifier = modifier.clickable {
                        viewModel.selectedPlayerCard(i)
                    },
                    hero = playerDeck[i]
                )
            }
        })
}

@Preview(showBackground = true)
@Composable
fun HeroCard(modifier: Modifier = Modifier, hero: DataHero = DataHero()) {

    Card(
        modifier = modifier
            .padding(16.dp)
            .shadow(5.dp)
    ) {
        Column(
            modifier = modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            /*Image(
                modifier = modifier
                    .clip(RoundedCornerShape(40.dp))
                    .padding(2.dp),
                painter = painterResource(R.drawable.default_imagen_heroe),
                contentDescription = "${hero.name} portrait",
                contentScale = ContentScale.Crop,

            )*/
            Box(modifier = modifier.size(190.dp)) {
                HeroImage(
                    url = hero.image.url
                )
            }
            Text(
                modifier = modifier.padding(2.dp),
                text = hero.name
            )
            HeroStats(
                modifier = modifier
                    .padding(2.dp),
                heroStats = hero.powerstats
            )
        }

    }

}

@Preview(showBackground = true)
@Composable
fun HeroItem(modifier: Modifier = Modifier, hero: DataHero = DataHero()) {
    Card(
        modifier = modifier
            .padding(16.dp, 8.dp, 16.dp, 8.dp)
            .shadow(8.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(2.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            /*Image(
                modifier = modifier
                    .size(125.dp)
                    .clip(RoundedCornerShape(40.dp))
                    .padding(2.dp),
                painter = painterResource(R.drawable.default_imagen_heroe),
                contentDescription = "${hero.name} portrait",
                contentScale = ContentScale.Crop
            )*/
            Box(
                modifier = modifier
                    .size(120.dp)
                    .padding(4.dp)
            ) {
                HeroImage(
                    url = hero.image.url
                )

            }
            Spacer(
                modifier = modifier.padding(8.dp)
            )
            Text(
                modifier = modifier.padding(2.dp),
                text = hero.name
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HeroStats(modifier: Modifier = Modifier, heroStats: Powerstats = Powerstats()) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = modifier) {
            Text(
                modifier = modifier,
                text = "Combate: ${heroStats.combat}",
                fontSize = 13.sp
            )
            Text(
                modifier = modifier,
                text = "Durabilidad: ${heroStats.durability}",
                fontSize = 13.sp
            )
            Text(
                modifier = modifier,
                text = "Poder: ${heroStats.power}",
                fontSize = 13.sp
            )
        }
        Column(modifier = modifier) {
            Text(
                modifier = modifier,
                text = "Velocidad: ${heroStats.speed}",
                fontSize = 13.sp
            )
            Text(
                modifier = modifier,
                text = "Fuerza: ${heroStats.strength}",
                fontSize = 13.sp
            )
            Text(
                modifier = modifier,
                text = "Inteligencia: ${heroStats.intelligence}",
                fontSize = 13.sp
            )
        }
    }
}


