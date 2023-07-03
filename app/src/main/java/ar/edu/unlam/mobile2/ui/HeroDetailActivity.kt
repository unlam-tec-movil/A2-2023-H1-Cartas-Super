package ar.edu.unlam.mobile2.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.unlam.mobile2.R
import ar.edu.unlam.mobile2.domain.hero.DataHero
import ar.edu.unlam.mobile2.domain.hero.HeroAppearance
import ar.edu.unlam.mobile2.domain.hero.HeroBiography
import ar.edu.unlam.mobile2.domain.hero.HeroConnections
import ar.edu.unlam.mobile2.domain.hero.HeroImage
import ar.edu.unlam.mobile2.domain.hero.HeroStats
import ar.edu.unlam.mobile2.domain.hero.HeroWork
import ar.edu.unlam.mobile2.ui.ui.theme.Mobile2_ScaffoldingTheme
import ar.edu.unlam.mobile2.ui.ui.theme.shaka_pow
import ar.edu.unlam.mobile2.ui.viewmodel.HeroDetailViewModelImp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HeroDetailActivity : ComponentActivity() {
    private val viewModel by viewModels<HeroDetailViewModelImp>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val myIntent: Intent = intent
        val idString: String? = myIntent.getStringExtra("id")
        viewModel.getHero(idString!!.toInt())
        setContent {
            Mobile2_ScaffoldingTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HeroDetailFondo()
                    HeroDetails(dataHero = viewModel.hero)
                }
            }
        }
    }
}

/* ////////////////////////////////////////////////////////////////////////////////////////////////
    REFACTORIZAR BOTONES PARA SIMPLIFICAR CÓDIGO //////////////////////////////////////////////////
 */////////////////////////////////////////////////////////////////////////////////////////////////

@Composable
fun HeroDetailFondo() {
    Image(
        painter = painterResource(id = R.drawable.fondo_coleccion),
        contentDescription = "Pantalla detalles del héroe",
        contentScale = ContentScale.FillHeight,
        modifier = Modifier.fillMaxSize()
    )
}

@Preview
@Composable
fun HeroDetails(
    modifier: Modifier = Modifier,
    dataHero: DataHero = DataHero(id = "9999", name = "Test 99999")
) {
    var isStatsVisible by rememberSaveable { mutableStateOf(true) }
    var isBiographyVisible by rememberSaveable { mutableStateOf(true) }
    var isAppearanceVisible by rememberSaveable { mutableStateOf(true) }
    var isWorkVisible by rememberSaveable { mutableStateOf(true) }
    var isConnectionsVisible by rememberSaveable { mutableStateOf(true) }

    Column(
        modifier = modifier
            .verticalScroll(state = rememberScrollState())
    ) {

        HeroImage(modifier = Modifier
            .padding(8.dp)
            .align(Alignment.CenterHorizontally)
            .fillMaxWidth(),
            url = dataHero.image.url
        )

        Row(
            modifier = modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "${dataHero.id} ${dataHero.name}")
        }
        Button(
            onClick = { isStatsVisible = !isStatsVisible },
            modifier = modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .padding(16.dp),
            colors = ButtonDefaults.buttonColors(Color.Red)
        ) {
            Text(
                text = if (isStatsVisible) "Ocultar Stats" else "Mostrar Stats",
                fontSize = 20.sp,
                color = Color.White,
                fontFamily = shaka_pow
            )
        }
        if (isStatsVisible) {
            HeroStats(stats = dataHero.powerstats)
        }

        Button(
            onClick = { isBiographyVisible = !isBiographyVisible },
            modifier = modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .padding(16.dp),
            colors = ButtonDefaults.buttonColors(Color.Red)
        ) {
            Text(
                text = if (isBiographyVisible) "Ocultar biografia" else "Mostrar biografia",
                fontSize = 20.sp,
                color = Color.White,
                fontFamily = shaka_pow
            )
        }
        if (isBiographyVisible) {
            HeroBiography(biography = dataHero.biography)
        }

        Button(
            onClick = { isAppearanceVisible = !isAppearanceVisible },
            modifier = modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .padding(16.dp),
            colors = ButtonDefaults.buttonColors(Color.Red)
        ) {
            Text(
                text = if (isAppearanceVisible) "Ocultar apariencia" else "mostrar apariencia",
                fontSize = 20.sp,
                color = Color.White,
                fontFamily = shaka_pow
            )
        }
        if (isAppearanceVisible) {
            HeroAppearance(heroAppearance = dataHero.appearance)
        }

        Button(
            onClick = { isWorkVisible = !isWorkVisible },
            modifier = modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .padding(16.dp),
            colors = ButtonDefaults.buttonColors(Color.Red)
        ) {
            Text(
                text = if (isWorkVisible) "Ocultar profesion" else "Mostrar profesi0n",
                fontSize = 20.sp,
                color = Color.White,
                fontFamily = shaka_pow
            )
        }
        if (isWorkVisible) {
            HeroWork(heroWork = dataHero.work)
        }

        Button(
            onClick = { isConnectionsVisible = !isConnectionsVisible },
            modifier = modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .padding(16.dp),
            colors = ButtonDefaults.buttonColors(Color.Red)
        ) {
            Text(
                text = if (isConnectionsVisible) "Ocultar connecciones" else "Mostrar conecciones",
                fontSize = 20.sp,
                color = Color.White,
                fontFamily = shaka_pow
            )
        }
        if (isConnectionsVisible) {
            HeroConnections(heroConnections = dataHero.connections)
        }
    }
}
