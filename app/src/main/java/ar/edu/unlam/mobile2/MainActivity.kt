package ar.edu.unlam.mobile2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ar.edu.unlam.mobile2.ui.screens.collection.CollectionScreen
import ar.edu.unlam.mobile2.ui.screens.herodetail.HeroDetailScreen
import ar.edu.unlam.mobile2.ui.screens.heroduel.HeroDuelScreen
import ar.edu.unlam.mobile2.ui.screens.home.HomeScreen
import ar.edu.unlam.mobile2.ui.screens.quiz.QuizScreen
import ar.edu.unlam.mobile2.ui.theme.ComicWarTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        run {
            CoroutineScope(Dispatchers.Main).launch {
                setContent {
                    ComicWarTheme(
                        darkTheme = isSystemInDarkTheme() /* es un boolean*/,
                        dynamicColor = true /*cambiar de ser necesario*/
                    ) {
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colorScheme.background
                        ) {
                            MainScreen()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    val controller = rememberNavController()
    Scaffold(
        floatingActionButton = {
            IconButton(onClick = { controller.navigate("home") }) {
                Icon(Icons.Filled.Home, contentDescription = "Home")
            }
        },
    ) { paddingValue ->
        NavHost(navController = controller, startDestination = "home") {
            composable("home") {
                HomeScreen(
                    modifier = Modifier.padding(paddingValue),
                    controller = controller
                )
            }
            composable("collection") {
                CollectionScreen(
                    modifier = Modifier.padding(paddingValue),
                    controller = controller
                )
            }
            composable(
                route = "herodetail/{heroid}",
                arguments = listOf(navArgument("heroid") { type = NavType.IntType }),
            ) { navBackStackEntry ->
                val heroID = navBackStackEntry.arguments?.getInt("heroid") ?: 1
                HeroDetailScreen(
                    modifier = Modifier.padding(paddingValue),
                    controller = controller,
                    heroID = heroID
                )
            }
            composable("quiz") {
                QuizScreen(
                    modifier = Modifier.padding(paddingValue),
                    controller = controller
                )
            }
            composable("duel") {
                HeroDuelScreen(
                    modifier = Modifier.padding(paddingValue),
                    controller = controller
                )
            }
        }
    }
}





