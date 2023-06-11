package ar.edu.unlam.mobile2.domain.hero

data class Biography(
    val aliases: List<String> = listOf(),
    val alignment: String = "NA",
    val alterEgos: String = "NA",
    val firstAppearance: String = "NA",
    val fullName: String = "NA",
    val placeOfBirth: String = "NA",
    val publisher: String = "NA"
)
