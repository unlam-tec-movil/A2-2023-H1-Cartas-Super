package ar.edu.unlam.mobile2

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.unlam.mobile2.ui.CollectionActivity
import ar.edu.unlam.mobile2.ui.HeroDuelActivity
import ar.edu.unlam.mobile2.ui.QuizActivity
import ar.edu.unlam.mobile2.ui.theme.Mobile2_ScaffoldingTheme
import ar.edu.unlam.mobile2.ui.theme.shaka_pow
import ar.edu.unlam.mobile2.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Log.i("MainActivity", "onCreate") dejo comentado esto para tener un ejemplo de logeo de datos

        run {
            CoroutineScope(Dispatchers.Main).launch {
                setContent {
                    PantallaFondo()
                    ContenidoPantalla()
                }
            }
        }
    }
}

@Composable
fun PantallaFondo() {
    Image(
        painter = painterResource(id = R.drawable.pantalla_principal),
        contentDescription = "Pantalla Coleccion",
        contentScale = ContentScale.FillWidth,
        modifier = Modifier.fillMaxSize()
    )
}


@Composable
fun ContenidoPantalla() {

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 70.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {

        Button(
            onClick = {
                context.startActivity(Intent(context, HeroDuelActivity::class.java))
            },
            modifier = Modifier.wrapContentSize().padding(16.dp),
            colors = ButtonDefaults.buttonColors(Color.Yellow)
        ) {
            Text(
                "START", fontSize = 20.sp,
                color = Color.Black,
                fontFamily = shaka_pow
            )
        }

        Button(
            onClick = {
                context.startActivity(Intent(context, CollectionActivity::class.java))
            },
            modifier = Modifier.wrapContentSize().padding(16.dp),
            colors = ButtonDefaults.buttonColors(Color.Yellow)
        ) {
            Text(
                "Coleccion", fontSize = 20.sp,
                color = Color.Black,
                fontFamily = shaka_pow
            )
        }

        Button(
            onClick = {
                context.startActivity(Intent(context, QuizActivity::class.java))
            },
            colors = ButtonDefaults.buttonColors(Color.Yellow)
        ) {
            Text(
                "Pregunta diaria", fontSize = 20.sp,
                color = Color.Black,
                fontFamily = shaka_pow
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
    Mobile2_ScaffoldingTheme {
        PantallaFondo()
        ContenidoPantalla()
    }
}

