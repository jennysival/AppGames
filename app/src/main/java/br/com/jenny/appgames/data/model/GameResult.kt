package br.com.jenny.appgames.data.model


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "games_table")
data class GameResult(
    @SerializedName("id")
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0,

    @SerializedName("added")
    var added: Int = 0,
//    @SerializedName("added_by_status")
//    var addedByStatus: AddedByStatus,
    @SerializedName("background_image")
    var backgroundImage: String = "",
//    @SerializedName("esrb_rating")
//    var esrbRating: EsrbRating,
    @SerializedName("metacritic")
    var metacritic: Int = 0,
    @SerializedName("name")
    var name: String = "",
//    @SerializedName("platforms")
//    var platforms: List<Platform>,
    @SerializedName("playtime")
    var playtime: Int = 0,
    @SerializedName("rating")
    var rating: Int = 0,
    @SerializedName("rating_top")
    var ratingTop: Int = 0,
//    @SerializedName("ratings")
//    var ratings: Ratings,
    @SerializedName("ratings_count")
    var ratingsCount: Int = 0,
    @SerializedName("released")
    var released: String = "",
    @SerializedName("reviews_text_count")
    var reviewsTextCount: String = "",
    @SerializedName("slug")
    var slug: String = "",
    @SerializedName("suggestions_count")
    var suggestionsCount: Int = 0,
    @SerializedName("tba")
    var tba: Boolean = false,
    @SerializedName("updated")
    var updated: String = "",

    var saved: Boolean = false
): Parcelable