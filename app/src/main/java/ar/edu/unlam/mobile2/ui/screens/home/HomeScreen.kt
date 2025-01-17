package ar.edu.unlam.mobile2.ui.screens.home

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import ar.edu.unlam.mobile2.R
import ar.edu.unlam.mobile2.ui.theme.shaka_pow

@Composable
fun NavigationButton(modifier : Modifier = Modifier,text: String = "button",onClick: () -> Unit) {
    Button(
        onClick = {
            onClick()
        },
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(Color.Yellow)
    ) {
        Text(
            text = text, fontSize = 20.sp,
            color = Color.Black,
            fontFamily = shaka_pow
        )
    }
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    controller: NavHostController
) {

    val navButtonModifier = Modifier.wrapContentSize().padding(16.dp)

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
            NavigationButton(
                modifier = navButtonModifier,
                text = "Duelo"
            ) {
                controller.navigate(route = "duel")
            }
            NavigationButton(
                modifier = navButtonModifier,
                text = "Quiz"
            ) {
                controller.navigate(route = "quiz")
            }
            NavigationButton(
                modifier = navButtonModifier,
                text = "Coleccion"
            ) {
                controller.navigate(route = "collection")
            }
        }
    }
}

