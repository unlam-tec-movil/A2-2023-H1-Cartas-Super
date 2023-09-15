package ar.edu.unlam.mobile2.ui.screens.collection

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
class CollectionViewModelImp @Inject constructor(private val repo: IHeroRepository):ViewModel() {

    private val _heroList = MutableStateFlow<List<DataHero>>(emptyList())
    val heroList = _heroList.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    init {
        viewModelScope.launch {
            _isLoading.value = true
            _heroList.value = withContext(Dispatchers.IO) {
                    repo.getAllHero()
                }
            _isLoading.value = false
        }
    }
}