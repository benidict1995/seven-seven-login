package com.benidict.a77_login.feature.signin

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.benidict.a77_login.NINJA_TAP_THROTTLE_TIME
import com.benidict.a77_login.R
import com.benidict.a77_login.base.BaseFragment
import com.benidict.a77_login.databinding.FragmentSigninBinding
import com.benidict.a77_login.ext.textChanged
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

@AndroidEntryPoint
class SignInFragment : BaseFragment<FragmentSigninBinding>(
    FragmentSigninBinding::inflate
) {

    private val viewModel: SignInViewModel by viewModels()

    override fun onSetUpView() {
        super.onSetUpView()
        binding.inputUserName.textChanged()
            .onEach {
                viewModel.userNameState.value = it.toString()
            }
            .launchIn(lifecycleScope)

        binding.inputPassword.textChanged()
            .onEach {
                viewModel.passwordState.value = it.toString()
            }
            .launchIn(lifecycleScope)

        binding.btnLogin.setOnClickListener {
            viewModel.login()
        }
    }

    @OptIn(FlowPreview::class)
    override fun setUpObserver() {
        super.setUpObserver()

        val isRequirementsCompleted =
            combine(viewModel.userNameState, viewModel.passwordState) { username, password ->
                username.isEmpty().not() and password.isEmpty().not()
            }

        viewLifecycleOwner.lifecycleScope.launch {
            isRequirementsCompleted
                .debounce(NINJA_TAP_THROTTLE_TIME.milliseconds)
                .collect {
                    binding.btnLogin.isEnabled = it
                }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect { state ->
                when (state) {
                    SignInState.NavigateToHome -> navigateToHome()
                    is SignInState.ShowError -> {
                        Toast.makeText(requireContext(), state.exception, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun navigateToHome() {
        findNavController().navigate(
            R.id.homeFragment,
            null,
            NavOptions.Builder()
                .setPopUpTo(
                    R.id.signInFragment,
                    true
                ).build()
        )
    }
}