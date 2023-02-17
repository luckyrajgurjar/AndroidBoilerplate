package com.daffodil.androidboilerplate.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.daffodil.androidboilerplate.R
import com.daffodil.androidboilerplate.base.BaseFragment
import com.daffodil.androidboilerplate.databinding.FragmentLoginBinding
import com.daffodil.androidboilerplate.viewmodel.MainViewModel

class LoginFragment : BaseFragment<FragmentLoginBinding,MainViewModel>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun getViewModelClass(): Class<MainViewModel> {
        TODO("Not yet implemented")
    }

    override fun getLayoutId(): FragmentLoginBinding {
        TODO("Not yet implemented")
    }

    override fun onViewReady(savedInstance: Bundle?) {
        TODO("Not yet implemented")
    }
}