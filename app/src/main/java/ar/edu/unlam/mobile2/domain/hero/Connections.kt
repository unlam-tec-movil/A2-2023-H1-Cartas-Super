package ar.edu.unlam.mobile2.domain.hero

import com.google.gson.annotations.SerializedName

data class Connections(
    @SerializedName("groupAffiliation") val groupAffiliation: String = "NA",
    @SerializedName("relatives") val relatives: String = "NA"
)