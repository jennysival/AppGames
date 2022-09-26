package br.com.jenny.appgames.ui.gameDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import br.com.jenny.appgames.R
import br.com.jenny.appgames.data.model.GameResult
import br.com.jenny.appgames.databinding.FragmentDetailBinding
import br.com.jenny.appgames.ui.home.HomeActivity
import br.com.jenny.appgames.utils.GAME_KEY
import com.squareup.picasso.Picasso

class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private lateinit var game: GameResult

    private val viewModel: DetailViewModel by lazy {
        ViewModelProvider(this)[DetailViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getGame()
        setUpView()
        setUpSaveButton()
    }

    private fun getGame() {
        val getGame = arguments?.getParcelable<GameResult>(GAME_KEY)

        if (getGame != null) {
            game = getGame

        } else {
            Toast.makeText(context, getString(R.string.txtErrorLoadGames), Toast.LENGTH_SHORT).show()
        }
    }

    private fun setUpView() {
        game.let {
            Picasso.get().load(it.backgroundImage).into(binding.ivGameDetail)
            binding.tvGameNameDetail.text = it.name
            binding.tvGameReleaseDetail.text = "Release: " + it.released
            updateSaveIcon()

            (activity as HomeActivity).supportActionBar?.title = it.name
        }
        (activity as HomeActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setUpSaveButton() {
        binding.iconSave.setOnClickListener {
            viewModel.insertSavedGame(game)
            game.saved = !game.saved
            updateSaveIcon()
            viewModel.updateSavedGame(game)
            setUpSavedToast()
            deleteSavedGame()
        }
    }

    private fun updateSaveIcon() {
        binding.iconSave.setImageDrawable(
            ContextCompat.getDrawable(
                binding.root.context,
                if (game.saved) R.drawable.ic_baseline_favorite_24 else R.drawable.ic_baseline_favorite_border_24
            )
        )
    }

    private fun deleteSavedGame(){
        if(!game.saved){
            viewModel.deleteSavedGame(game)
        }
    }

    private fun setUpSavedToast(){
        if(game.saved){
            Toast.makeText(context, "${game.name} foi salvo com sucesso", Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(context, "${game.name} foi excluído", Toast.LENGTH_SHORT).show()
        }
    }

}