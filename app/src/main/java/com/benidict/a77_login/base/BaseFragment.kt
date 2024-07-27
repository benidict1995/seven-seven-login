package com.benidict.a77_login.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB: ViewBinding>(
    private val setUpBinding: (LayoutInflater) -> VB,
) : Fragment(){

    lateinit var binding: VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = setUpBinding(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onLoadContent()
        onSetUpView()
        setUpObserver()
    }

    open fun setUpObserver(){}
    open fun onLoadContent(){}
    open fun onSetUpView(){}

}