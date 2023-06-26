package ar.edu.unlam.mobile2.domain.hero

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ar.edu.unlam.mobile2.ui.ui.theme.shaka_pow
import com.google.gson.annotations.SerializedName

data class Appearance(
    @SerializedName("eyeColor") val eyeColor: String = "NA",
    @SerializedName("gender") val gender: String = "NA",
    @SerializedName("hairColor") val hairColor: String = "NA",
    @SerializedName("height") val height: List<String> = listOf("NA"),
    @SerializedName("race") val race: String = "NA",
    @SerializedName("weight") val weight: List<String> = listOf("NA")
)

@Preview(showBackground = true)
@Composable
fun HeroAppearance(modifier: Modifier = Modifier, heroAppearance: Appearance = Appearance() ) {
    Column(
        modifier = modifier
            .background(
                brush = SolidColor(Color.Black),
                alpha = 0.8f
            ).padding(8.dp),
        horizontalAlignment = Alignment.Start
    ) {
        AppearanceText(text = "Genero biologico", value = heroAppearance.gender)
        AppearanceText(text = "Raza", value = heroAppearance.race)
        AppearanceText(text = "Altura", value = heroAppearance.height.toString())
        AppearanceText(text = "Peso", value = heroAppearance.weight.toString())
        AppearanceText(text = "Color de ojos", value = heroAppearance.eyeColor)
        AppearanceText(text = "Color de cabello", value = heroAppearance.hairColor)
    }
}

@Composable
fun AppearanceText(modifier:Modifier = Modifier,text:String = "Raza",value:String = "White") {
    Text(
        modifier = modifier,
        text = "$text: $value",
        fontFamily = shaka_pow,
        color = Color.White
    )
}