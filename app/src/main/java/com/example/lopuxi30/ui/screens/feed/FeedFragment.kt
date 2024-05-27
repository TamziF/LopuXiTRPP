package com.example.lopuxi30.ui.screens.feed

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.lopuxi30.R
import com.example.lopuxi30.databinding.FragmentFeedBinding
import com.example.lopuxi30.ui.stateholders.FeedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeedFragment : Fragment() {

    private lateinit var binding: FragmentFeedBinding

    private val viewModel: FeedViewModel by viewModels()

    private var viewController: FeedFragmentViewController? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFeedBinding.inflate(inflater, container, false)

        viewModel.token = getToken()

        viewController = FeedFragmentViewController(
            binding,
            this,
            viewModel
        ).apply {
            setupViews()
        }

        return binding.root
    }

    override fun onDestroyView() {

        viewController?.clear()
        viewController = null

        super.onDestroyView()
    }

    private fun getToken(): String {
        val sharedPreferences: SharedPreferences by lazy {
            requireContext().getSharedPreferences(
                requireContext().getString(R.string.SharedPrefs),
                Context.MODE_PRIVATE
            )
        }
        return sharedPreferences.getString(
            requireContext().getString(R.string.SharedPrefs_Token),
            ""
        )!!
    }
}