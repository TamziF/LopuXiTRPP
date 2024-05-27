package com.example.lopuxi30.ui.stateholders

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lopuxi30.R
import com.example.lopuxi30.data.models.AuthRegBody
import com.example.lopuxi30.data.repositories.AuthRegRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class AuthRegStatus { LOADING, DONE, ERROR, DEFAULT }

@HiltViewModel
class AuthRegViewModel @Inject constructor(
    private val authRegRepository: AuthRegRepository
) : ViewModel() {

    private val authRegBody: AuthRegBody = AuthRegBody("", "")

    private val _buttonEnabled: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val buttonEnabled: StateFlow<Boolean> = _buttonEnabled

    private val _passwordsEqual: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val passwordsEqual: StateFlow<Boolean> = _passwordsEqual

    private val _authRegStatus: MutableStateFlow<AuthRegStatus> = MutableStateFlow(AuthRegStatus.DEFAULT)
    val authRegStatus: StateFlow<AuthRegStatus> = _authRegStatus


    fun setUsername(username: String) {
        authRegBody.username = username
        checkButtonEnable()
    }

    fun setPassword(password: String) {
        authRegBody.password = password
        checkButtonEnable()
    }

    fun submitPassword(password: String) {
        _passwordsEqual.value = authRegBody.password == password
        checkButtonEnable()
    }

    fun authorize(context: Context) {
        _authRegStatus.value = AuthRegStatus.LOADING
        viewModelScope.launch(Dispatchers.IO) {
            val response = authRegRepository.authorize(authRegBody)
            if (response.isSuccessful && response.body() != null) {
                val token = response.body()!!.token
                saveToSharedPrefs(token, context)
                _authRegStatus.value = AuthRegStatus.DONE
            } else {
                _authRegStatus.value = AuthRegStatus.ERROR
            }
        }
    }

    fun register(context: Context) {
        _authRegStatus.value = AuthRegStatus.LOADING
        viewModelScope.launch(Dispatchers.IO) {
            val response = authRegRepository.register(authRegBody)
            if (response.isSuccessful) {
                authorize(context)
            } else {
                _authRegStatus.value = AuthRegStatus.ERROR
            }
        }
    }

    private fun saveToSharedPrefs(token: String, context: Context) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(
            context.getString(R.string.SharedPrefs),
            Context.MODE_PRIVATE
        )
        val editor = sharedPreferences.edit()
        editor.putString(context.getString(R.string.SharedPrefs_Token), "Bearer $token")
        editor.putString(context.getString(R.string.SharedPrefs_Username), authRegBody.username)
        editor.putString(context.getString(R.string.SharedPrefs_Password), authRegBody.password)
        editor.apply()
    }

    private fun checkButtonEnable() {
        _buttonEnabled.value =
            authRegBody.username.isNotBlank() && authRegBody.password.isNotBlank() && _passwordsEqual.value
    }

}