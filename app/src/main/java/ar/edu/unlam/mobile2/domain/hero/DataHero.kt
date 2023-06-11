package ar.edu.unlam.mobile2.domain.hero

data class DataHero(
    val appearance: Appearance = Appearance(),
    val biography: Biography = Biography(),
    val connections: Connections = Connections(),
    val id: String = "NA",
    val image: Image = Image(),
    val name: String = "NA",
    val powerstats: Powerstats = Powerstats(),
    val response: String = "NA",
    val work: Work = Work(),
    val isFavorite: Boolean = false
)