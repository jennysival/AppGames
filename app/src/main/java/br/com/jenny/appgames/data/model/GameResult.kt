package br.com.jenny.appgames.data.model


import com.google.gson.annotations.SerializedName

data class GameResult(
    @SerializedName("added")
    var added: Int,
    @SerializedName("added_by_status")
    var addedByStatus: AddedByStatus,
    @SerializedName("background_image")
    var backgroundImage: String,
    @SerializedName("esrb_rating")
    var esrbRating: EsrbRating,
    @SerializedName("id")
    var id: Int,
    @SerializedName("metacritic")
    var metacritic: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("platforms")
    var platforms: List<Platform>,
    @SerializedName("playtime")
    var playtime: Int,
    @SerializedName("rating")
    var rating: Int,
    @SerializedName("rating_top")
    var ratingTop: Int,
    @SerializedName("ratings")
    var ratings: Ratings,
    @SerializedName("ratings_count")
    var ratingsCount: Int,
    @SerializedName("released")
    var released: String,
    @SerializedName("reviews_text_count")
    var reviewsTextCount: String,
    @SerializedName("slug")
    var slug: String,
    @SerializedName("suggestions_count")
    var suggestionsCount: Int,
    @SerializedName("tba")
    var tba: Boolean,
    @SerializedName("updated")
    var updated: String
)