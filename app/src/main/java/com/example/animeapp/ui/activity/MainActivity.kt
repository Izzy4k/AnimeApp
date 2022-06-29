package com.example.animeapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.animeapp.R
import com.example.animeapp.databinding.ActivityMainBinding
import com.example.animeapp.ultils.ext.gone
import com.example.animeapp.ultils.ext.isOnline
import com.example.animeapp.ultils.ext.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val controller: NavController by lazy {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navHostFragment.navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initBtn()
        initBottomNavigation()
        controllerObserver()
    }

    private fun initBtn() {
        binding.internetCheck.btnEmpty.setOnClickListener {
            checkInternet()
        }
    }

    private fun controllerObserver() {
        controller.addOnDestinationChangedListener { _, dest, _ ->
            checkInternet()
            if (dest.id == R.id.detailFragment) {
                binding.botNav.gone()
            }else{
                binding.botNav.visible()
            }
        }
    }

    private fun checkInternet() {
        if (isOnline()) binding.contInternet.gone() else binding.contInternet.visible()
    }

    private fun initBottomNavigation() {
        binding.botNav.apply {
            setupWithNavController(controller)
            itemIconTintList = null
        }
    }
}