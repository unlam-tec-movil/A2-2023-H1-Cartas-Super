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

data class Biography(
    @SerializedName("aliases") val aliases: List<String> = listOf("NA"),
    @SerializedName("alignment") val alignment: String = "NA",
    @SerializedName("alterEgos") val alterEgos: String = "NA",
    @SerializedName("firstAppearance") val firstAppearance: String = "NA",
    @SerializedName("fullName") val fullName: String = "NA",
    @SerializedName("placeOfBirth") val placeOfBirth: String = "NA",
    @SerializedName("publisher") val publisher: String = "NA"
)

@Preview(showBackground = true)
@Composable
fun HeroBiography(modifier: Modifier = Modifier, biography: Biography = Biography()) {
    Column(
        modifier = modifier
            .background(
                brush = SolidColor(Color.Black),
                alpha = 0.8f
            )
            .padding(8.dp),
        horizontalAlignment = Alignment.Start
    ) {
        BiographyText(text = "Nombre",value = biography.fullName)
        BiographyText(text = "Alter-Egos",value = biography.alterEgos)
        BiographyText(text = "Apodos",value = biography.aliases.toString())
        BiographyText(text = "Lugar de nacimiento",value = biography.placeOfBirth)
        BiographyText(text = "Primera aparicion",value = biography.firstAppearance)
        BiographyText(text = "Editorial",value = biography.publisher)
        BiographyText(text = "Alineacion",value = biography.alignment)
    }
}

@Composable
fun BiographyText(
    modifier:Modifier = Modifier,
    text:String = "Nombre",
    value:String = "Clark Kent"
) {
   Text(
       modifier = modifier,
       text = "$text: $value",
       fontFamily = shaka_pow,
       color = Color.White
   )
}