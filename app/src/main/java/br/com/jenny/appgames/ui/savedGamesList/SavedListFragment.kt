package br.com.jenny.appgames.ui.savedGamesList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import br.com.jenny.appgames.R
import br.com.jenny.appgames.data.model.GameResult
import br.com.jenny.appgames.databinding.FragmentSavedListBinding
import br.com.jenny.appgames.state.ViewState
import br.com.jenny.appgames.ui.home.HomeActivity
import br.com.jenny.appgames.utils.GAME_KEY

class SavedListFragment : Fragment() {
    private lateinit var binding: FragmentSavedListBinding
    private val viewModel: SavedListViewModel by lazy{
        ViewModelProvider(this)[SavedListViewModel::class.java]
    }
    private val adapter: SavedListAdapter by lazy{
        SavedListAdapter(mutableListOf(), this::goToGameDetail)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSavedListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpActionBar()
        initObserver()
        viewModel.getSavedGames()
        setUpRecyclerView()
    }

    private fun setUpActionBar(){
        (activity as HomeActivity).supportActionBar?.title = getString(R.string.title_saved)
        (activity as HomeActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initObserver(){
        viewModel.savedListState.observe(this.viewLifecycleOwner){
            when(it){
                is ViewState.Success -> {
                    if(it.data.isEmpty()){
                        adapter.updateSavedGamesList(it.data as MutableList<GameResult>)
                        Toast.makeText(context, "Não há jogos salvos!", Toast.LENGTH_LONG).show()
                    }
                    else{
                        adapter.updateSavedGamesList(it.data as MutableList<GameResult>)
                    }
                }
                is ViewState.Error -> {
                    Toast.makeText(context, "${it.throwable.message}", Toast.LENGTH_LONG).show()
                }
                else -> {}
            }
        }

        viewModel.loading.observe(this.viewLifecycleOwner){
            when(it){
                is ViewState.Loading -> {
                    binding.pbLoad.isVisible = it.loading == true
                }
                else -> {}
            }
        }
    }

    private fun setUpRecyclerView(){
        binding.rvSavedGamesList.adapter = this.adapter
        binding.rvSavedGamesList.layoutManager = GridLayoutManager(context, 2)
    }

    private fun goToGameDetail(game: GameResult){
        val bundle = bundleOf(GAME_KEY to game)
        NavHostFragment.findNavController(this).navigate(R.id.action_savedListFragment_to_detailFragment, bundle)
    }

}