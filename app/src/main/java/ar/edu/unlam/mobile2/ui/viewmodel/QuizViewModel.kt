package ar.edu.unlam.mobile2.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile2.data.repository.IHeroRepository
import ar.edu.unlam.mobile2.domain.quiz.QuizGame
import ar.edu.unlam.mobile2.domain.quiz.QuizOption
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(repo:IHeroRepository) : ViewModel() {

    private lateinit var game:QuizGame

    private val _isLoadingLD : MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val isLoadingLD : LiveData<Boolean> = _isLoadingLD

    private val _heroPortraitUrlD : MutableLiveData<String> =
        MutableLiveData<String>("https://loremflickr.com/400/400/cat?lock=1")
    val heroPortraitUrlD : LiveData<String> = _heroPortraitUrlD

    private val _option1LD : MutableLiveData<String> = MutableLiveData("")
    val option1LD : LiveData<String> = _option1LD

    private val _option2LD:MutableLiveData<String> = MutableLiveData("")
    val option2LD : LiveData<String> = _option2LD

    private val _option3LD:MutableLiveData<String> = MutableLiveData("")
    val option3LD: LiveData<String> = _option3LD

    private val _option4LD:MutableLiveData<String> = MutableLiveData("")
    val option4LD: LiveData<String> = _option4LD

    private val _showResultLD:MutableLiveData<Boolean> = MutableLiveData(false)
    val showResultLD: LiveData<Boolean> = _showResultLD

    private val _isCorrectAnswerLD:MutableLiveData<Boolean> = MutableLiveData(false)
    val isCorrectAnswerLD: LiveData<Boolean> = _isCorrectAnswerLD

    var isLoading by mutableStateOf(false)
        private set
    var heroPortraitUrl by mutableStateOf("https://loremflickr.com/400/400/cat?lock=1")
        private set
    var option1 by mutableStateOf("")
        private set
    var option2 by mutableStateOf("")
        private set
    var option3 by mutableStateOf("")
        private set
    var option4 by mutableStateOf("")
        private set
    var showResult by mutableStateOf(false)
        private set
    var isCorrectAnswer by mutableStateOf(false)
        private set

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoadingLD.value = true
            isLoading = true
            game = QuizGame(repo.getRandomPlayerDeck(4))
            usingComposeState()
            usingLiveData()
            _isLoadingLD.value = false
            isLoading = false
        }
    }

    private fun usingComposeState() {
        heroPortraitUrl = game.correctAnswer.image.url
        option1 = game.option1.name
        option2 = game.option2.name
        option3 = game.option3.name
        option4 = game.option4.name
    }

    private fun usingLiveData() {
        _heroPortraitUrlD.value = game.correctAnswer.image.url
        _option1LD.value = game.option1.name
        _option2LD.value = game.option2.name
        _option3LD.value = game.option3.name
        _option4LD.value = game.option4.name
    }

    fun selectOption1() {
        selectOption(QuizOption.OPTION_1)
    }

    fun selectOption2() {
        selectOption(QuizOption.OPTION_2)
    }

    fun selectOption3() {
        selectOption(QuizOption.OPTION_3)
    }

    fun selectOption4() {
        selectOption(QuizOption.OPTION_4)
    }

    private fun selectOption(option:QuizOption) {
        isCorrectAnswer = game.isCorrectAnswer(option)
        _isCorrectAnswerLD.value = game.isCorrectAnswer(option)
        showResult = true
        _showResultLD.value = true
    }
}