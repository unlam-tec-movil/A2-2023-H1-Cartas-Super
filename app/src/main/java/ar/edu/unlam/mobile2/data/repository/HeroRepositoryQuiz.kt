package ar.edu.unlam.mobile2.data.repository

import ar.edu.unlam.mobile2.domain.hero.DataHero
import javax.inject.Inject

class HeroRepositoryQuiz @Inject constructor(){

    private fun heroListTestQuiz() : List<DataHero> {
        val hero1 = DataHero(
            name = "Superman",
            //url = "https://www.superherodb.com/pictures2/portraits/10/100/791.jpg"
        )
        val hero2 = DataHero(
            name = "Flash",
            //url = "https://www.superherodb.com/pictures2/portraits/10/100/891.jpg"
        )
        val hero3 = DataHero(
            name = "Goku",
            //url = "https://www.superherodb.com/pictures2/portraits/10/100/1284.jpg"
        )
        val hero4 = DataHero(
            name = "Hawkeye",
            //url = "https://www.superherodb.com/pictures2/portraits/10/100/73.jpg"
        )
        val hero5 = DataHero(
            name = "Batman",
           // url = "https://www.superherodb.com/pictures2/portraits/10/100/639.jpg"
        )
        val hero6 = DataHero(
            name = "Iron Man",
            //url = "https://www.superherodb.com/pictures2/portraits/10/100/814.jpg"
        )


        return listOf()
    }

     fun getPlayerList(): List<DataHero> {
        return heroListTestQuiz()
    }
}