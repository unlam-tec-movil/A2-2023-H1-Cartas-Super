package ar.edu.unlam.mobile2.domain.hero

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ar.edu.unlam.mobile2.ui.ui.theme.shaka_pow
import com.google.gson.annotations.SerializedName
import java.time.format.TextStyle

data class Powerstats(
    @SerializedName("combat") val combat: String = "000",
    @SerializedName("durability") val durability: String = "000",
    @SerializedName("intelligence") val intelligence: String = "000",
    @SerializedName("power") val power: String = "000",
    @SerializedName("speed") val speed: String = "000",
    @SerializedName("strength") val strength: String = "000"
)

@Preview(showBackground = true)
@Composable
fun HeroStats(modifier: Modifier = Modifier, stats: Powerstats = Powerstats()) {
    Row(
        modifier = modifier
            .background(
                brush = SolidColor(Color.Black),
                alpha = 0.8f
            ),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            StatComposable(statName = "Inteligencia", statValue = stats.intelligence)
            StatComposable(statName = "Velocidad", statValue = stats.speed)
            StatComposable(statName = "Durabilidad", statValue = stats.durability)
        }
        Column(
            modifier = modifier.padding(8.dp)
        ) {
            StatComposable(statName = "Fuerza", statValue = stats.strength)
            StatComposable(statName = "Poder", statValue = stats.power)
            StatComposable(statName = "Combate", statValue = stats.combat)
        }
    }
}

@Composable
fun StatComposable(
    modifier:Modifier = Modifier,
    statName:String = "Inteligencia",
    statValue:String = "000"
) {
    Text(
        modifier = modifier,
        text = "$statName: $statValue",
        fontFamily = shaka_pow,
        color = Color.White
    )
}