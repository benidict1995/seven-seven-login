package com.benidict.a77_login.feature.home

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.benidict.a77_login.R
import com.benidict.a77_login.base.BaseFragment
import com.benidict.a77_login.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment: BaseFragment<FragmentHomeBinding>(
    FragmentHomeBinding::inflate
) {

    private val viewModel: HomeViewModel by viewModels()

    override fun onLoadContent() {
        super.onLoadContent()
        viewModel.loadUserProfile()
    }

    override fun setUpObserver() {
        super.setUpObserver()
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect { state ->
                when(state) {
                    is HomeState.LoadUserProfile -> {
                        binding.tvWelcome.text = getString(R.string.welcome, state.user.name)
                    }
                }
            }
        }
    }

}