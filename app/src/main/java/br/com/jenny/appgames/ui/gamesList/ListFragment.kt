package br.com.jenny.appgames.ui.gamesList

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
import br.com.jenny.appgames.databinding.FragmentListBinding
import br.com.jenny.appgames.state.ViewState
import br.com.jenny.appgames.ui.home.HomeActivity
import br.com.jenny.appgames.utils.GAME_KEY

class ListFragment : Fragment() {
    private lateinit var binding: FragmentListBinding

    private val viewModel: ListViewModel by lazy {
        ViewModelProvider(this)[ListViewModel::class.java]
    }

    private val adapter: GamesListAdapter by lazy {
        GamesListAdapter(mutableListOf(), this::goToGameDetail)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpActionBar()
        viewModel.getAllGamesNetwork(1, 20)
        initObserver()
        setUpRecyclerView()
        setUpSavedButton()
    }

    private fun setUpActionBar(){
        (activity as HomeActivity).supportActionBar?.title = getString(R.string.title_app)
        (activity as HomeActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    private fun initObserver() {
        viewModel.gamesListState.observe(this.viewLifecycleOwner){
            when(it){
                is ViewState.Success -> {
                    adapter.updateGamesList(it.data as MutableList<GameResult>)
                }
                is ViewState.Error -> {
                    Toast.makeText(context, "${it.throwable.message}", Toast.LENGTH_SHORT).show()
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

    private fun setUpRecyclerView() {
        binding.rvGamesList.adapter = adapter
        binding.rvGamesList.layoutManager = GridLayoutManager(context,2)
    }

    private fun goToGameDetail(game: GameResult) {
        val bundle = bundleOf(GAME_KEY to game)
        NavHostFragment.findNavController(this).navigate(R.id.action_listFragment_to_detailFragment, bundle)
    }

    private fun setUpSavedButton() {
        binding.saveButton.setOnClickListener {
            NavHostFragment.findNavController(this)
                .navigate(R.id.action_listFragment_to_savedListFragment)
        }
    }

}