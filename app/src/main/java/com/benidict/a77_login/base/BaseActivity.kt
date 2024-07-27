package com.benidict.a77_login.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB: ViewBinding>(
    val setUpBinding: (LayoutInflater) -> VB
) : AppCompatActivity(){

    lateinit var viewBinding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = setUpBinding(layoutInflater)
        setContentView(viewBinding.root)

        onLoadContent()
        onSetUpView()
    }

    open fun onLoadContent(){}
    open fun onSetUpView(){}
}