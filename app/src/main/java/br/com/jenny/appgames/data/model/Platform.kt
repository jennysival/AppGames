package br.com.jenny.appgames.data.model


import com.google.gson.annotations.SerializedName

data class Platform(
    @SerializedName("platform")
    var platform: PlatformX,
    @SerializedName("released_at")
    var releasedAt: String,
    @SerializedName("requirements")
    var requirements: Requirements
)