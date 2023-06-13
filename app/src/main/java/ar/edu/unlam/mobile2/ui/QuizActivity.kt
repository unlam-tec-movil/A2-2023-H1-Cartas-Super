package ar.edu.unlam.mobile2.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.unlam.mobile2.R
import ar.edu.unlam.mobile2.ui.ui.theme.Mobile2_ScaffoldingTheme
import ar.edu.unlam.mobile2.ui.ui.theme.shaka_pow

class Quiz : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Mobile2_ScaffoldingTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PantallaQuiz()
                    QuizItem()
                }
            }
        }
    }
}

@Composable
fun PantallaQuiz() {
    Image(
        painter = painterResource(id = R.drawable.fondo_coleccion),
        contentDescription = "Pantalla Coleccion",
        contentScale = ContentScale.FillHeight,
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
fun QuizItem(
    idHeroe: Int = 9999,
    nombreHeroe: String = "test",
    esFavorito: Boolean = false,
    painterResourceId: Int = R.drawable.default_imagen_heroe
) {

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            Text(
                "Quien es este personaje?",
                fontSize = 30.sp,
                color = Color.White,
                fontFamily = shaka_pow,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .border(2.dp, Color.Red, CircleShape)
                    .background(Color.Red)
                    .padding(6.dp),
                textAlign = TextAlign.Center,

                )
            Box(
                modifier = Modifier
                    .padding(18.dp, 40.dp, 18.dp, 60.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(Color.Red)
                    .wrapContentSize(align = Alignment.TopCenter)

            ) {

                Column {
                    Image(
                        painter = painterResource(id = painterResourceId),
                        contentDescription = "Imagen heroe",
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .align(Alignment.CenterHorizontally)
                            .padding(70.dp)
                    )
                    Text(
                        nombreHeroe,
                        color = Color.White,
                        fontFamily = shaka_pow,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                }
                Text(
                    idHeroe.toString(), color = Color.White,
                    fontFamily = shaka_pow,
                    fontSize = 20.sp, modifier = Modifier.padding(8.dp)
                )
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxSize(1f)
                        .padding(8.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.icon_favorite_not),
                        contentDescription = "icono no favorito",
                        modifier = Modifier
                    )
                }

            }

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { /* Acción del botón 1 */ },
                    Modifier
                        .width(180.dp)
                        .height(80.dp)
                        .padding(8.dp),
                    colors = ButtonDefaults.buttonColors(Color.Red)
                ) {
                    Text(
                        "Personaje 1", fontSize = 18.sp,
                        color = Color.White,
                        fontFamily = shaka_pow
                    )
                }
                Button(
                    onClick = { /* Acción del botón 2 */ },
                    Modifier
                        .width(180.dp)
                        .height(80.dp)
                        .padding(8.dp),
                    colors = ButtonDefaults.buttonColors(Color.Red)
                ) {
                    Text(
                        "Personaje 2", fontSize = 18.sp,
                        color = Color.White,
                        fontFamily = shaka_pow
                    )
                }
            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { /* Acción del botón 3 */ },
                    Modifier
                        .width(180.dp)
                        .height(80.dp)
                        .padding(8.dp),
                    colors = ButtonDefaults.buttonColors(Color.Red)
                ) {
                    Text(
                        "Personaje 3", fontSize = 18.sp,
                        color = Color.White,
                        fontFamily = shaka_pow
                    )
                }
                Button(
                    onClick = { /* Acción del botón 4 */ },
                    Modifier
                        .width(180.dp)
                        .height(80.dp)
                        .padding(8.dp),
                    colors = ButtonDefaults.buttonColors(Color.Red)
                ) {
                    Text(
                        "Personaje 4", fontSize = 18.sp,
                        color = Color.White,
                        fontFamily = shaka_pow
                    )
                }
            }


        }

    }

}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Mobile2_ScaffoldingTheme {
        PantallaQuiz()
        QuizItem()
    }
}