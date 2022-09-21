package br.com.jenny.appgames

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import br.com.jenny.appgames.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setFragmentNavigation()
        setActionBar()
    }

    private fun setFragmentNavigation(){
        supportFragmentManager.findFragmentById(binding.navHostFragmentContainer.id) as NavHostFragment
    }

    private fun setActionBar(){
        supportActionBar?.setTitle(R.string.title_gameX)
    }
}