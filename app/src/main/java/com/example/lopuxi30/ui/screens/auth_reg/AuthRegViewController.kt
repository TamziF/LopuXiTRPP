package com.example.lopuxi30.ui.screens.auth_reg

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import com.example.lopuxi30.R
import com.example.lopuxi30.databinding.FragmentAuthRegBinding
import com.example.lopuxi30.ui.screens.MainActivity
import com.example.lopuxi30.ui.stateholders.AuthRegStatus
import com.example.lopuxi30.ui.stateholders.AuthRegViewModel
import kotlinx.coroutines.launch

class AuthRegViewController(
    private val viewModel: AuthRegViewModel,
    private var fragment: AuthRegFragment?,
    binding: FragmentAuthRegBinding
) {

    private val usernameEt = binding.usernameEt
    private val passwordEt = binding.passwordEt
    private val repeatPasswordEt = binding.repeatPasswordEt
    private val authRegButton = binding.authRegButton
    private val changeModeTv = binding.toSignUpTv

    private val errorImage = binding.passwordsAreNotEqualErrorImage
    private val errorMessage = binding.passwordsAreNotEqualMessage

    private val loadingImage = binding.loadingImage

    private val repeatPasswordLayout = binding.repeatPasswordLayout

    private var isAuthMode: Boolean = true

    fun setupViews() {
        setupLoginEt()
        setupPasswordEt()
        setupRepeatPasswordEt()

        setupAuthRegButton()
        setupChangeModeTv()

        setupErrorMessageListener()
        bindAuthRegStatus()
    }

    private fun bindAuthRegStatus() {
        fragment!!.viewLifecycleOwner.lifecycleScope.launch {
            fragment!!.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.authRegStatus.collect { status ->
                    when (status) {
                        AuthRegStatus.LOADING -> {
                            authRegButton.visibility = View.INVISIBLE
                            loadingImage.visibility = View.VISIBLE
                        }
                        AuthRegStatus.DONE -> {
                            doneState()
                        }
                        AuthRegStatus.ERROR -> {
                            showMessage("Error")
                            authRegButton.visibility = View.VISIBLE
                            loadingImage.visibility = View.INVISIBLE
                        }
                        AuthRegStatus.DEFAULT -> {
                            authRegButton.visibility = View.VISIBLE
                            loadingImage.visibility = View.INVISIBLE
                        }
                    }
                }
            }
        }
    }

    private fun doneState() {
        val activity = fragment!!.requireActivity() as MainActivity
        activity.showBottomNavigationView()

        Navigation.findNavController(fragment!!.requireView()).navigate(R.id.action_authRegFragment_to_feedFragment)
    }

    private fun showMessage(message: String) {
        Toast.makeText(fragment!!.requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun setupLoginEt() {
        usernameEt.addTextChangedListener {
            viewModel.setUsername(it.toString())
        }
    }

    private fun setupPasswordEt() {
        passwordEt.addTextChangedListener {
            viewModel.setPassword(it.toString())
        }
    }

    private fun setupRepeatPasswordEt() {
        repeatPasswordEt.addTextChangedListener {
            viewModel.submitPassword(it.toString())
        }
    }

    private fun setupAuthRegButton() {
        fragment!!.lifecycleScope.launch {
            fragment!!.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.buttonEnabled.collect { isEnabled ->
                    authRegButton.isEnabled = isEnabled
                }
            }
        }

        authRegButton.setOnClickListener {
            if (isAuthMode)
                viewModel.authorize(fragment!!.requireContext())
            else
                viewModel.register(fragment!!.requireContext())
        }
    }

    private fun setupChangeModeTv() {
        changeModeTv.setOnClickListener {
            isAuthMode = !isAuthMode

            if (isAuthMode)
                setAuthMode()
            else
                setRegMode()
        }
    }

    private fun setupErrorMessageListener() {
        fragment!!.lifecycleScope.launch {
            fragment!!.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.passwordsEqual.collect { arePasswordEqual ->
                    if (arePasswordEqual)
                        hideErrorMessage()
                    else
                        showErrorMessage()
                }
            }
        }
    }

    private fun showErrorMessage() {
        errorImage.visibility = View.VISIBLE
        errorMessage.visibility = View.VISIBLE
    }

    private fun hideErrorMessage() {
        errorImage.visibility = View.GONE
        errorMessage.visibility = View.GONE
    }

    private fun setAuthMode() {
        repeatPasswordEt.visibility = View.GONE
        repeatPasswordLayout.visibility = View.GONE
        authRegButton.text = fragment!!.getString(R.string.sign_in)

        changeModeTv.text = fragment!!.getString(R.string.sign_up)
        hideErrorMessage()
    }

    private fun setRegMode() {
        repeatPasswordEt.visibility = View.VISIBLE
        repeatPasswordLayout.visibility = View.VISIBLE
        authRegButton.text = fragment!!.getString(R.string.sign_up)

        changeModeTv.text = fragment!!.getString(R.string.sign_in)
    }

    fun clear() {
        fragment = null
    }

}