package ar.edu.unlam.mobile2.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import ar.edu.unlam.mobile2.ui.screens.herodetail.HeroDetailScreen
import ar.edu.unlam.mobile2.ui.theme.ComicWarTheme
import ar.edu.unlam.mobile2.ui.screens.herodetail.HeroDetailViewModelImp
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
            ComicWarTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HeroDetailScreen(modifier = Modifier.fillMaxSize())
                }
            }
        }
    }
}
