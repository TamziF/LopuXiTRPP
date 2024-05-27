package com.example.lopuxi30.ui.screens.auth_reg

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.lopuxi30.databinding.FragmentAuthRegBinding
import com.example.lopuxi30.ui.stateholders.AuthRegViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthRegFragment : Fragment() {

    private val viewModel: AuthRegViewModel by viewModels()
    private lateinit var binding: FragmentAuthRegBinding

    private var fragmentViewController: AuthRegViewController? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthRegBinding.inflate(inflater, container, false)

        fragmentViewController = AuthRegViewController(
            viewModel,
            this,
            binding
        ).apply {
            setupViews()
        }

        return binding.root
    }

    override fun onDestroyView() {

        fragmentViewController?.clear()
        fragmentViewController = null

        super.onDestroyView()
    }

}