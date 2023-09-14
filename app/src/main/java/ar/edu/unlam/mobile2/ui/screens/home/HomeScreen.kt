package ar.edu.unlam.mobile2.ui.screens.home

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ar.edu.unlam.mobile2.R
import ar.edu.unlam.mobile2.ui.CollectionActivity
import ar.edu.unlam.mobile2.ui.HeroDuelActivity
import ar.edu.unlam.mobile2.ui.QuizActivity
import ar.edu.unlam.mobile2.ui.theme.shaka_pow
import ar.edu.unlam.mobile2.ui.viewmodel.MainViewModel

@Composable
fun HomeScreen(modifier: Modifier = Modifier, viewmodel: MainViewModel = hiltViewModel()) {

    val context = LocalContext.current
    Box(modifier = modifier) {
        Image(
            painter = painterResource(id = R.drawable.pantalla_principal),
            contentDescription = "Pantalla Coleccion",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxSize()
        )
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
                modifier = Modifier
                    .wrapContentSize()
                    .padding(16.dp),
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
                modifier = Modifier
                    .wrapContentSize()
                    .padding(16.dp),
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
}