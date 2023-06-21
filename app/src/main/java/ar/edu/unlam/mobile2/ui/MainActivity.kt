package ar.edu.unlam.mobile2.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import androidx.lifecycle.Observer
import androidx.navigation.ui.AppBarConfiguration
import ar.edu.unlam.mobile2.R
import ar.edu.unlam.mobile2.databinding.ActivityMainBinding
import ar.edu.unlam.mobile2.ui.collection.CollectionActivity
import ar.edu.unlam.mobile2.ui.heroDuel.HeroDuelActivity
import ar.edu.unlam.mobile2.ui.quiz.QuizActivity
import ar.edu.unlam.mobile2.ui.ui.theme.Mobile2_ScaffoldingTheme
import ar.edu.unlam.mobile2.ui.ui.theme.shaka_pow
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : /*AppCompatActivity()*/ ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("MainActivity", "onCreate")

        mainViewModel.kittyUrl.observe(
            this,
            Observer<String> { _ ->
                run {
                    CoroutineScope(Dispatchers.Main).launch {
                        Log.i("MainActivity", "Observer")
                        setContent {
                            PantallaFondo()
                            ContenidoPantalla()
                            //content(name = "Mundo")
                        }
                    }
                }
            },
        )
    }

    override fun onStart() {
        super.onStart()
        Log.i("MainActivity", "onStart")
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
            /*Button(
                onClick = {
                    context.startActivity(Intent(context, HeroDuelActivity::class.java))
                },
            ) {
                Text(text = "ir pantalla HeroDuel")
            }*/

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
}


// @Composable
// fun content(name: String) {
// val context = LocalContext.current
// Log.i("MainActivity", "start content")
// Column() {
// Log.i("MainActivity", "button row")
// Row() {
// Button(
// onClick = {
// Log.i("Button", "Presiono el boton")
// mainViewModel.updateKittyUrl()
// },
// ) {
// Text(text = "Actualizar imagen")
// }
// Button(
// onClick = {
// context.startActivity(Intent(context, CollectionActivity::class.java))
// },
// ) {
// Text(text = "ir pantalla Collection")
// }
//
// }
//
// Log.i("MainActivity", "first row")
// Row() {
// Text(text = "Imagen de los gatitos")
// Button(
// onClick = {
// context.startActivity(Intent(context, HeroDetailActivity::class.java))
// },
// ) {
// Text(text = "ir pantalla heroDetail")
// }
// }
// Log.i("MainActivity", "second row")
// Row() {
// SubcomposeAsyncImage(
// model = mainViewModel.getImageRequest(context),
// // placeholder = painterResource(R.drawable.placeholder),
// contentDescription = stringResource(R.string.cat_image),
// contentScale = ContentScale.FillBounds,
// loading = {
// CircularProgressIndicator(
// modifier = Modifier
// .height(50.dp)
// .width(50.dp),
// )
// },
// modifier = Modifier
// .height(300.dp)
// .width(500.dp),
// // error = rememberAsyncImagePainter(model = mainViewModel.DEFAULT),
// onError = { error ->
// run {
// mainViewModel.updateKittyUrl()
// Log.e("AsyncImageError", error.result.throwable.message.toString())
// }
// },
// onLoading = { state ->
// if (BuildConfig.DEBUG) {
// Log.i(
// "AsyncImageLoading",
// state.toString(),
// )
// }
// },
//
// )
// }
// Log.i("MainActivity", "third row")
// Row() {
// Text(text = "Pie de imagen")
// }
// }
// }
//
// override fun onCreateOptionsMenu(menu: Menu): Boolean {
// // Inflate the menu; this adds items to the action bar if it is present.
// menuInflater.inflate(R.menu.menu_main, menu)
// return true
// }
//
// override fun onOptionsItemSelected(item: MenuItem): Boolean {
// // Handle action bar item clicks here. The action bar will
// // automatically handle clicks on the Home/Up button, so long
// // as you specify a parent activity in AndroidManifest.xml.
// return when (item.itemId) {
// R.id.action_settings -> true
// else -> super.onOptionsItemSelected(item)
// }
// }
//
// /*override fun onSupportNavigateUp(): Boolean {
// val navController = findNavController(R.id.nav_host_fragment_content_main)
// return navController.navigateUp(appBarConfiguration)
// || super.onSupportNavigateUp()
// }*/