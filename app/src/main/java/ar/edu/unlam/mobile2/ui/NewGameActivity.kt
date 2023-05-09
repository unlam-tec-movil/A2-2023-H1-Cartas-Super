package ar.edu.unlam.mobile2.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ar.edu.unlam.mobile2.R
import ar.edu.unlam.mobile2.domain.difficulty.Difficulty
import ar.edu.unlam.mobile2.ui.ui.theme.Mobile2_ScaffoldingTheme

class NewGameActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Mobile2_ScaffoldingTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NewGameFondo()
                    NewGame(
                        difficultyList = listDiffTest(),
                        deckList = deckListTest(),
                        deckSizeLimit = 9999
                    )
                }
            }
        }
    }
}

@Composable
fun NewGameFondo() {
    Image(
        painter = painterResource(id = R.drawable.fondo_pantalla_pelea),
        contentDescription = "Pantalla NewGame",
        contentScale = ContentScale.FillBounds,
        modifier = Modifier.fillMaxSize()
    )
}

fun listDiffTest(): List<Difficulty> {
    return listOf(
        Difficulty.EASY,
        Difficulty.NORMAL,
        Difficulty.HARD
    )
}

@Preview
@Composable
fun NewGamePreview() {
    NewGame(difficultyList = listDiffTest(), deckList = deckListTest(), deckSizeLimit = 9999)
}

@Composable
fun NewGame(modifier: Modifier = Modifier,
            difficultyList: List<Difficulty>,
            deckList: List<String>,
            deckSizeLimit: Int
            ) {
    Column {
        Text(
            modifier = modifier,
            text = "Dificultad"
        )
        DifficultyDropDownMenu(modifier = modifier, listDifficulty = difficultyList)

        Text(modifier = modifier, text = "Mazo")
        DeckDropDownMenu(deckList = deckList)

        Text("Tamaño del mazo (Entre 1 y $deckSizeLimit)")
        DeckSize(modifier = modifier, limit = deckSizeLimit)
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DifficultyDropDownMenu(modifier: Modifier = Modifier, listDifficulty: List<Difficulty>) {
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(listDifficulty[0]) }
    val context = LocalContext.current

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            TextField(
                value = selectedText.difficultyName,
                onValueChange = { /* TODO */ },
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = modifier.menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                listDifficulty.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item.difficultyName) },
                        onClick = {
                            selectedText = item
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

fun deckListTest() : List<String> {
    return listOf("Aleatorio", "Personalizado 1", "Personalizado 2")
}

@Preview
@Composable
fun DeckSelectionPreview() {
    DeckDropDownMenu(deckList = deckListTest())
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeckDropDownMenu(modifier: Modifier = Modifier, deckList: List<String>) {
    var expanded by remember { mutableStateOf(false) }
    var selectedMazo by remember { mutableStateOf(deckList[0]) }
    val context = LocalContext.current

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            TextField(
                value = selectedMazo,
                onValueChange = { /* TODO */ },
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = modifier.menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                deckList.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
                            selectedMazo = item
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun DeckSizePreview() {
    DeckSize(limit = 9999)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeckSize(modifier: Modifier = Modifier, limit: Int) {
    var value by remember { mutableStateOf("1") }
    val context = LocalContext.current
    TextField(
        value = value,
        onValueChange = {
            value = it
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        label = { Text("Introduzca tamaño del mazo") },
        maxLines = 1,
        modifier = modifier
    )

}