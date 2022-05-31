package com.example.fourtaxis_graduateapp.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.fourtaxis_graduateapp.R
import com.example.fourtaxis_graduateapp.databinding.ActivityMainBinding
import com.example.fourtaxis_graduateapp.presentation.login_register.LoginRegisterViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: LoginRegisterViewModel

    private val component by lazy {
        (application as FourTaxisApplication).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)

        viewModel = ViewModelProvider(this, viewModelFactory)[LoginRegisterViewModel::class.java]

        observeBottomMenuVisibility()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNavigationView.setupWithNavController(navController)

    }

    private fun observeBottomMenuVisibility() {
        viewModel.isUserAuthenticated.observe(this) {
            if (it) {
                binding.bottomNavigationView.visibility = View.VISIBLE
            } else {
                binding.bottomNavigationView.visibility = View.GONE
            }
        }
    }
}