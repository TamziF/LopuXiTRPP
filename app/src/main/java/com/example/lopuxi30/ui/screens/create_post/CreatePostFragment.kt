package com.example.lopuxi30.ui.screens.create_post

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.lopuxi30.R
import com.example.lopuxi30.databinding.FragmentCreatePostBinding
import com.example.lopuxi30.ui.stateholders.CreatePostViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreatePostFragment : Fragment() {

    private val viewModel: CreatePostViewModel by viewModels()
    private lateinit var binding: FragmentCreatePostBinding

    private var fragmentViewController: CreatePostFragmentViewController? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreatePostBinding.inflate(layoutInflater, container, false)

        viewModel.token = getToken()
        viewModel.setUsername(getUsername())

        fragmentViewController = CreatePostFragmentViewController(
            binding,
            this,
            viewModel
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

    private val sharedPreferences: SharedPreferences by lazy {
        requireContext().getSharedPreferences(
            requireContext().getString(R.string.SharedPrefs),
            Context.MODE_PRIVATE
        )
    }

    private fun getToken(): String {
        return sharedPreferences.getString(
            requireContext().getString(R.string.SharedPrefs_Token),
            ""
        )!!
    }

    private fun getUsername(): String {
        return sharedPreferences.getString(
            requireContext().getString(R.string.SharedPrefs_Username),
            ""
        )!!
    }
}