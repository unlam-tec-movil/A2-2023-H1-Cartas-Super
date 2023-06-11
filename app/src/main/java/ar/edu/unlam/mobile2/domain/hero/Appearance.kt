package ar.edu.unlam.mobile2.domain.hero

data class Appearance(
    val eyeColor: String = "NA",
    val gender: String = "NA",
    val hairColor: String = "NA",
    val height: List<String> = listOf(),
    val race: String = "NA",
    val weight: List<String> = listOf()
)
