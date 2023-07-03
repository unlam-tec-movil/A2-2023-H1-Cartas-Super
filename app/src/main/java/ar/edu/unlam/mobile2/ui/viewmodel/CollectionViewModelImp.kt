package ar.edu.unlam.mobile2.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile2.data.repository.IHeroRepository
import ar.edu.unlam.mobile2.ui.utilities.HeroListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CollectionViewModelImp @Inject constructor(private val repo: IHeroRepository):ViewModel() {
    var heroList by mutableStateOf(HeroListState())
        private set

    init {
        viewModelScope.launch(Dispatchers.IO) {
            heroList = heroList.copy(
                isLoading = true
            )
            heroList = heroList.copy(
                heroList = repo.getAllHero(),
                isLoading = false
            )
        }
    }

}