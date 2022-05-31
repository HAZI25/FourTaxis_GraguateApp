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
import com.example.fourtaxis_graduateapp.databinding.FragmentRegisterBinding
import com.example.fourtaxis_graduateapp.presentation.FourTaxisApplication
import com.example.fourtaxis_graduateapp.presentation.ViewModelFactory
import javax.inject.Inject

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
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
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, viewModelFactory)[LoginRegisterViewModel::class.java]

        observeInputError()
        setupClickListeners()
        addTextChangeListeners()
    }

    private fun addTextChangeListeners() {
        binding.etFullName.doOnTextChanged { _, _, _, _ ->
            viewModel.resetErrorInputName()
        }
        binding.etPhoneNumber.doOnTextChanged { _, _, _, _ ->
            viewModel.resetErrorInputPhone()
        }
        binding.etEmail.doOnTextChanged { _, _, _, _ ->
            viewModel.resetErrorInputEmail()
        }
        binding.etPassword.doOnTextChanged { _, _, _, _ ->
            viewModel.resetErrorInputPassword()
        }
    }

    private fun setupClickListeners() {
        binding.btnRegister.setOnClickListener {
            val name = binding.etFullName.text.toString()
            val phone = binding.etPhoneNumber.text.toString()
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            viewModel.registerUser(name, phone, email, password)
        }
        binding.tvLogin.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun observeInputError() {
        viewModel.errorInputName.observe(viewLifecycleOwner) {
            val message = if (it) getString(R.string.error_input_name) else null
            binding.tilName.error = message
        }
        viewModel.errorInputPhone.observe(viewLifecycleOwner) {
            val message = if (it) getString(R.string.error_input_phone) else null
            binding.tilPhone.error = message
        }
        viewModel.errorInputEmail.observe(viewLifecycleOwner) {
            val message = if (it) getString(R.string.error_input_email) else null
            binding.tilEmail.error = message
        }
        viewModel.errorInputPassword.observe(viewLifecycleOwner) {
            val message = if (it) getString(R.string.error_input_password) else null
            binding.tilPassword.error = message
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}