package ar.edu.unlam.mobile2.domain.hero

import com.google.gson.annotations.SerializedName

data class Powerstats(
    @SerializedName("combat") val combat: String = "NA",
    @SerializedName("durability") val durability: String = "NA",
    @SerializedName("intelligence") val intelligence: String = "NA",
    @SerializedName("power") val power: String = "NA",
    @SerializedName("speed") val speed: String = "NA",
    @SerializedName("strength") val strength: String = "NA"
)