package ar.edu.unlam.mobile2.domain.hero

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("url") val url: String = "NA"
)
