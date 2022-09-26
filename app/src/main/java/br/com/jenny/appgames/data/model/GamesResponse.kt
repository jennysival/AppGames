package br.com.jenny.appgames.data.model


import com.google.gson.annotations.SerializedName

data class GamesResponse(
    @SerializedName("count")
    var count: Int,
    @SerializedName("next")
    var next: String,
    @SerializedName("previous")
    var previous: String,
    @SerializedName("results")
    var gameResults: List<GameResult>
)