package com.daffodil.androidboilerplate.views

import android.os.Bundle
import com.daffodil.androidboilerplate.base.BaseActivity
import com.daffodil.androidboilerplate.databinding.ActivityMainBinding
import com.daffodil.androidboilerplate.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding,MainViewModel>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun getViewModelClass(): Class<MainViewModel> =MainViewModel::class.java

    override fun getLayoutId(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    override fun onViewReady(savedInstance: Bundle?) {
    }
}