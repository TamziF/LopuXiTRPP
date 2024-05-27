package com.example.lopuxi30.ui.screens.feed

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lopuxi30.databinding.FragmentFeedBinding
import com.example.lopuxi30.ui.recyclers.feed.FeedAdapter
import com.example.lopuxi30.ui.stateholders.FeedViewModel
import kotlinx.coroutines.launch

class FeedFragmentViewController(
    binding: FragmentFeedBinding,
    private var fragment: FeedFragment?,
    private val viewModel: FeedViewModel
) {

    private val recycler = binding.recyclerView
    private val swipeRefreshLayout = binding.swipeRefreshLayout

    fun setupViews() {
        viewModel.getFeed()

        bindRecycler()
        bindSwipeRefreshLayout()
    }

    private fun bindSwipeRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener {
            viewModel.refresh()
            swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun bindRecycler() {
        val adapter = FeedAdapter(viewModel.token)
        recycler.layoutManager = LinearLayoutManager(fragment!!.requireContext())
        recycler.adapter = adapter

        fragment!!.viewLifecycleOwner.lifecycleScope.launch {
            fragment!!.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.posts.collect { list ->
                    Log.v("HUY", list.size.toString())
                    adapter.submitList(list)
                }
            }
        }

    }

    fun clear() {
        fragment = null
    }

}