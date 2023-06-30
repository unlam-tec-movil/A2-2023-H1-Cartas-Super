package ar.edu.unlam.mobile2.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import ar.edu.unlam.mobile2.R
import ar.edu.unlam.mobile2.domain.hero.HeroImage
import ar.edu.unlam.mobile2.ui.ui.theme.shaka_pow
import ar.edu.unlam.mobile2.ui.ui.theme.Mobile2_ScaffoldingTheme
import ar.edu.unlam.mobile2.ui.viewmodel.QuizViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuizActivity : ComponentActivity() {
    private val viewModel by viewModels<QuizViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Mobile2_ScaffoldingTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PantallaQuiz()
                    //QuizUI(viewModel = viewModel, modifier = Modifier.fillMaxSize())
                    QuizUILiveData(viewModel = viewModel, modifier = Modifier.fillMaxSize())
                }
            }
        }
    }
}

@Composable
fun PantallaQuiz() {
    Image(
        painter = painterResource(id = R.drawable.fondo_coleccion),
        contentDescription = "Pantalla Coleccion",
        contentScale = ContentScale.FillBounds,
        modifier = Modifier.fillMaxSize()
    )
}


@Composable
fun PopupResult(isCorrectAnswer:Boolean = false, show:Boolean = false) {
    val context = LocalContext.current
    val textToShow = if(isCorrectAnswer) {
        "¡Felicidades! Tu respuesta es la correcta."
    }else{
        "Lamentablemente tu respuesta es incorrecta."
    }
    if(show) {
        AlertDialog(
            onDismissRequest = {},
            title = { Text("Resultado") },
            text = { Text(text = textToShow) },
            confirmButton = {
                TextButton(onClick = { context.startActivity(Intent(context, MainActivity::class.java))}) {
                    Text("Menú principal".uppercase())
                }
            },
            properties = DialogProperties(dismissOnBackPress = false,dismissOnClickOutside = false)
        )
    }
}

@Composable
fun QuizUILiveData(modifier:Modifier = Modifier,viewModel: QuizViewModel) {
    val offset = Offset(6.0f, 4.0f)
    val isLoading by viewModel.isLoadingLD.observeAsState(initial = true)
    val imageUrl by viewModel.heroPortraitUrlD.observeAsState()
    val option1Text by viewModel.option1LD.observeAsState(initial = "option1")
    val option2Text by viewModel.option2LD.observeAsState(initial = "option2")
    val option3Text by viewModel.option3LD.observeAsState(initial = "option3")
    val option4Text by viewModel.option4LD.observeAsState(initial = "option4")
    if(isLoading) {
        Box(modifier = modifier.fillMaxSize()) {
            CircularProgressIndicator(modifier = modifier.align(Alignment.Center))
        }
    }else{
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "¿Quien es este heroe?",
                color = Color.White,
                fontFamily = shaka_pow,
                fontSize = 30.sp,
                modifier = Modifier.padding(18.dp),
                style = TextStyle(
                    fontSize = 18.sp,
                    shadow = Shadow(
                        color = Color.Black,
                        offset = offset,
                        blurRadius = 4f
                    )
                )
            )
            HeroImage(
                modifier = Modifier
                    .aspectRatio(ratio = 1f)
                    .padding(8.dp),
                url = imageUrl!!
            )
            AnswerPanel(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                option1Text = option1Text,
                option2Text = option2Text,
                option3Text = option3Text,
                option4Text = option4Text,
                onOption1Click = viewModel::selectOption1,
                onOption2Click = viewModel::selectOption2,
                onOption3Click = viewModel::selectOption3,
                onOption4Click = viewModel::selectOption4
            )
        }
    }
    PopupResult(isCorrectAnswer = viewModel.isCorrectAnswer, show = viewModel.showResult)
}

@Composable
fun QuizUI(modifier:Modifier = Modifier,viewModel: QuizViewModel){
    if(viewModel.isLoading) {
        Box(modifier = modifier.fillMaxSize()) {
            CircularProgressIndicator(modifier = modifier.align(Alignment.Center))
        }
    }else{
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "¿Quien es este heroe?",
                color = Color.White,
                fontFamily = shaka_pow,
                fontSize = 24.sp,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            HeroImage(
                modifier = Modifier
                    .aspectRatio(ratio = 1f)
                    .padding(8.dp),
                url = viewModel.heroPortraitUrl
            )
            AnswerPanel(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                option1Text = viewModel.option1,
                option2Text = viewModel.option2,
                option3Text = viewModel.option3,
                option4Text = viewModel.option4,
                onOption1Click = viewModel::selectOption1,
                onOption2Click = viewModel::selectOption2,
                onOption3Click = viewModel::selectOption3,
                onOption4Click = viewModel::selectOption4
            )
        }
    }
    PopupResult(isCorrectAnswer = viewModel.isCorrectAnswer, show = viewModel.showResult)
}

@Preview(showBackground = true, widthDp = 600)
@Composable
fun AnswerPanel(
    modifier:Modifier = Modifier,
    onOption1Click:() -> Unit = {},
    onOption2Click:() -> Unit = {},
    onOption3Click:() -> Unit = {},
    onOption4Click:() -> Unit = {},
    option1Text:String = "option 1",
    option2Text:String = "option 2",
    option3Text:String = "option 3",
    option4Text:String = "option 4",
    ) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
        ) {
            AnswerButton(text = option1Text, onButtonClick = onOption1Click)
            Spacer(modifier = Modifier.padding(4.dp))
            AnswerButton(text = option3Text, onButtonClick = onOption3Click)
        }
        Column {
            AnswerButton(text = option2Text, onButtonClick = onOption2Click)
            Spacer(modifier = Modifier.padding(4.dp))
            AnswerButton(text = option4Text, onButtonClick = onOption4Click)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AnswerButton(
    modifier:Modifier = Modifier,
    text:String = "text",
    onButtonClick: () -> Unit = {}
) {
    Button(
        onClick = onButtonClick,
        modifier = modifier
            .width(180.dp)
            .height(80.dp),
        colors = ButtonDefaults.buttonColors(Color.Red)
    ) {
        Text(
            text = text, fontSize = 20.sp,
            color = Color.White,
            fontFamily = shaka_pow,
            textAlign = TextAlign.Center
        )
    }
}