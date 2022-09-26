package br.com.jenny.appgames.ui.gamesList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.jenny.appgames.data.model.GameResult
import br.com.jenny.appgames.databinding.GameItemBinding
import com.squareup.picasso.Picasso

class GamesListAdapter(
    private var gamesList: MutableList<GameResult> = mutableListOf(),
    private val click: (game: GameResult) -> Unit
) : RecyclerView.Adapter<GamesListAdapter.ViewHolder>() {

    class ViewHolder(val binding: GameItemBinding): RecyclerView.ViewHolder(binding.root){
        fun showGame(game: GameResult){
            var name = game.name
            binding.tvGameName.text = name
            Picasso.get().load(game.backgroundImage).into(binding.ivGame)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = GameItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val game = gamesList[position]
        holder.showGame(game)
        holder.binding.cvGame.setOnClickListener {
            click(game)
        }
    }

    override fun getItemCount() = gamesList.size

    fun updateGamesList(newList: MutableList<GameResult>){
        var size = gamesList.size
        gamesList = newList
        var sizeNew = gamesList.size
        notifyDataSetChanged()
        notifyItemRangeChanged(size, sizeNew)
    }
}