package ar.edu.unlam.mobile2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile2.data.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SuperheroViewModel : ViewModel() {
    private val _heroImage = MutableStateFlow<String?>(null)
    val heroImage: StateFlow<String?> = _heroImage

    fun fetchHeroImage(id: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.superheroApiService.getHeroImage(id)
                if (response.isSuccessful) {
                    _heroImage.value = response.body()?.url
                    println("conecto")
                } else {
                    println("Entro al else")
                    // Manejar errores de solicitud aquí
                }
            } catch (e: Exception) {
                // Manejar errores de conexión aquí
                println("Entro al catch")
            }
        }
    }
}

