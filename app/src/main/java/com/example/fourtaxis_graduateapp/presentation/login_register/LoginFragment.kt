package com.example.fourtaxis_graduateapp.presentation.login_register

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.fourtaxis_graduateapp.R
import com.example.fourtaxis_graduateapp.databinding.FragmentLoginBinding
import com.example.fourtaxis_graduateapp.presentation.FourTaxisApplication
import com.example.fourtaxis_graduateapp.presentation.ViewModelFactory
import javax.inject.Inject

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: LoginRegisterViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as FourTaxisApplication).component
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, viewModelFactory)[LoginRegisterViewModel::class.java]

        setupClickListeners()
        observeInputError()
        addTextChangeListeners()
        observeAuth()
    }

    private fun observeAuth() {
        viewModel.isUserAuthenticated.observe(viewLifecycleOwner) {
            if (it)
                findNavController().navigate(R.id.action_loginFragment_to_ridesFragment)
        }
    }

    private fun setupClickListeners() {
        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            viewModel.login(email, password)
        }

        binding.tvRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    private fun observeInputError() {
        viewModel.errorInputEmail.observe(viewLifecycleOwner) {
            val message = if (it) getString(R.string.error_input_email) else null
            binding.tilEmail.error = message
        }
        viewModel.errorInputPassword.observe(viewLifecycleOwner) {
            val message = if (it) getString(R.string.error_input_password) else null
            binding.tilPassword.error = message
        }
    }

    private fun addTextChangeListeners() {
        binding.etEmail.doOnTextChanged { _, _, _, _ ->
            viewModel.resetErrorInputEmail()
        }
        binding.etPassword.doOnTextChanged { _, _, _, _ ->
            viewModel.resetErrorInputPassword()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}