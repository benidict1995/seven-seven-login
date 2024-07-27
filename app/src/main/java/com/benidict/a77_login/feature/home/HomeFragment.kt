package com.benidict.a77_login.feature.home

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.benidict.a77_login.R
import com.benidict.a77_login.base.BaseFragment
import com.benidict.a77_login.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(
    FragmentHomeBinding::inflate
) {

    private val viewModel: HomeViewModel by viewModels()

    override fun onSetUpView() {
        super.onSetUpView()

        binding.toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.menu_logout -> {
                    viewModel.logout()
                    findNavController().navigate(
                        R.id.signInFragment,
                        null,
                        NavOptions.Builder()
                            .setPopUpTo(
                                R.id.homeFragment,
                                true
                            ).build()
                    )
                }
            }
            true
        }
    }

    override fun onLoadContent() {
        super.onLoadContent()
        viewModel.loadUserProfile()
    }

    override fun setUpObserver() {
        super.setUpObserver()
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect { state ->
                when (state) {
                    is HomeState.LoadUserProfile -> {
                        binding.tvWelcome.text = getString(R.string.welcome, state.user.name)
                    }
                }
            }
        }
    }

}