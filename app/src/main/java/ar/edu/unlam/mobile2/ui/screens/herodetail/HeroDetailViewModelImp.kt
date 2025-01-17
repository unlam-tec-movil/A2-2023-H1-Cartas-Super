package ar.edu.unlam.mobile2.ui.screens.herodetail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile2.data.repository.IHeroRepository
import ar.edu.unlam.mobile2.domain.hero.DataHero
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HeroDetailViewModelImp @Inject constructor(private val repo: IHeroRepository): ViewModel() {

    var hero by mutableStateOf(DataHero())
        private set

    private var isLoading = MutableStateFlow(true)
    val _isLoading = isLoading.asStateFlow()

    fun getHero(id: Int) {
        viewModelScope.launch {
            isLoading.value = true
            hero = withContext(Dispatchers.IO) {
                repo.getHero(id)
            }
            isLoading.value = false
        }
    }
}