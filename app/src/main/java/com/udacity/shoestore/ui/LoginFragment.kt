package com.udacity.shoestore.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         val binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.login.setOnClickListener {
            goToWelcomePage(it)
        }

        binding.createNew.setOnClickListener {
            goToWelcomePage(it)
        }

        return binding.root
    }

    private fun goToWelcomePage(view: View) {
        view.findNavController()
            .navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment("moved"))
    }
}