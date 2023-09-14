package ar.edu.unlam.mobile2.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import ar.edu.unlam.mobile2.ui.screens.heroduel.HeroDuelScreen
import ar.edu.unlam.mobile2.ui.theme.ComicWarTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HeroDuelActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComicWarTheme(
                darkTheme = isSystemInDarkTheme() /* es un boolean*/,
                dynamicColor = true /*cambiar de ser necesario*/
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HeroDuelScreen(
                        modifier = Modifier.fillMaxSize(),
                        controller = controller
                    )
                }
            }
        }
    }
}

