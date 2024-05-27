package com.example.lopuxi30.ui.stateholders

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lopuxi30.data.data_sources.network.BASE_URL
import com.example.lopuxi30.data.data_sources.network.Network
import com.example.lopuxi30.data.models.Post
import com.example.lopuxi30.data.repositories.FeedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Long
import java.sql.Timestamp
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val feedRepositoryImpl: FeedRepository
) : ViewModel() {

    var token = ""

    private var requestNumber = 0

    private val _posts: MutableStateFlow<ArrayList<Post>> = MutableStateFlow(arrayListOf())
    val posts: StateFlow<ArrayList<Post>> = _posts

    fun getFeed() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = feedRepositoryImpl.getFeed(token, "${BASE_URL}feed/$requestNumber")
            if (response.isSuccessful && response.body() != null) {
                Log.v("HUY", "До ${_posts.value.size}")
                val list = arrayListOf<Post>()
                list.addAll(_posts.value)
                list.addAll(response.body()!!)
                _posts.value = list
                Log.v("HUY", "После ${_posts.value.size}")
                requestNumber++

                if(response.body()!!.size == 5) getFeed()
            }
        }
    }

    fun refresh() {
        requestNumber = 0
        _posts.value = arrayListOf()
        getFeed()
    }

}