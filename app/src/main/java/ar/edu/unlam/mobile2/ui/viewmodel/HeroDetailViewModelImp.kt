package ar.edu.unlam.mobile2.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile2.data.repository.IHeroRepository
import ar.edu.unlam.mobile2.domain.hero.DataHero
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HeroDetailViewModelImp @Inject constructor(private val repo: IHeroRepository): ViewModel() {

    var hero by mutableStateOf(DataHero())
        private set

    fun getHero(id: Int) {
        viewModelScope.launch {
            hero = withContext(Dispatchers.IO) {
                repo.getHero(id)
            }
        }
    }
}