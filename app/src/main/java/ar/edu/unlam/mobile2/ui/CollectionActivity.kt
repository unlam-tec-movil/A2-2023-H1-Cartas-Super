package ar.edu.unlam.mobile2.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import ar.edu.unlam.mobile2.ui.screens.PantallaCollection
import ar.edu.unlam.mobile2.ui.ui.theme.Mobile2_ScaffoldingTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CollectionActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Mobile2_ScaffoldingTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PantallaCollection(modifier = Modifier.fillMaxSize())
                }
            }
        }
    }
}


