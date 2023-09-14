package ar.edu.unlam.mobile2.ui.screens.collection

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ar.edu.unlam.mobile2.R
import ar.edu.unlam.mobile2.domain.hero.DataHero
import ar.edu.unlam.mobile2.ui.HeroDetailActivity
import ar.edu.unlam.mobile2.ui.components.hero.HeroImage

@Preview
@Composable
fun CollectionScreen(modifier: Modifier = Modifier, viewModel: CollectionViewModelImp = hiltViewModel()) {
    Box(modifier = modifier){
        Image(
            painter = painterResource(id = R.drawable.fondo_coleccion),
            contentDescription = "Pantalla Coleccion",
            contentScale = ContentScale.FillHeight,
            modifier = Modifier.fillMaxSize()
        )
        if(viewModel.heroList.isLoading) {
                CircularProgressIndicator(modifier = modifier.align(Alignment.Center))
        } else {
            Galeria(modifier = Modifier.fillMaxSize(), heroList = viewModel.heroList.heroList)
        }
    }

}

fun dataHeroTestList(): List<DataHero> {
    val dataHeroTestList = mutableListOf<DataHero>()
    for(i in 0..9999) {
        dataHeroTestList.add(
            DataHero(name = "Test $i", id = "$i", isFavorite = i%3 == 0)
        )
    }
    return dataHeroTestList
}

@Preview
@Composable
fun GaleriaPreview() {
    Galeria(heroList = dataHeroTestList())
}

@Composable
fun Galeria(
    modifier: Modifier = Modifier,
    heroList: List<DataHero>,
    itemSize: Dp = 100.dp
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(itemSize),
        content = {
            items(heroList.size) {i ->
                GaleriaItem(
                    modifier = modifier,
                    dataHero = heroList[i],
                )
            }
        })
}



@Preview
@Composable
fun GaleriaItem(modifier: Modifier = Modifier,
                dataHero: DataHero = DataHero(),
                painterDefaultResourceId: Int = R.drawable.default_imagen_heroe
) {
    val context = LocalContext.current
    val myIntent = Intent(context, HeroDetailActivity::class.java)
    myIntent.putExtra("id",dataHero.id)
    TextButton(
        shape = RectangleShape,
        onClick = {
            context.startActivity(myIntent) }
    ) {
        Box(
            modifier = modifier
                .background(brush = SolidColor(Color.Black), alpha = 0.25f)
        ) {
            Column {
                HeroImage(
                    url = dataHero.image.url
                )
                /*
                Image(
                    painter = painterResource(id = painterDefaultResourceId),
                    contentDescription = "Imagen heroe",
                    modifier = modifier
                        .fillMaxWidth(1f)
                        .align(Alignment.CenterHorizontally)
                        .padding(8.dp)
                )

                 */
                Text(dataHero.name,
                    modifier = modifier
                        .padding(bottom = 8.dp)
                        .align(Alignment.CenterHorizontally),
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
            }
            Text(
                text = dataHero.id,
                modifier = modifier.padding(8.dp),
                color = Color.Black
            )
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = modifier
                    .fillMaxWidth(1f)
                    .padding(8.dp)
            ) {
                Image(
                    painter = painterResource(id = if(dataHero.isFavorite)
                        R.drawable.icon_favorite else R.drawable.icon_favorite_not
                    ),
                    contentDescription = if(dataHero.isFavorite) "icono favorito" else "icono no favorito",
                    modifier = modifier
                )
            }
        }
    }
}
