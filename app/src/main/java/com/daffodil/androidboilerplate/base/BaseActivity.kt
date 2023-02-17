package com.daffodil.androidboilerplate.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.viewbinding.ViewBinding
import com.daffodil.androidboilerplate.data.preferences.PrefHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

abstract class BaseActivity<binding : ViewBinding, viewmodel : ViewModel>: AppCompatActivity() , CoroutineScope {

    @Inject
    lateinit var prefHelper: PrefHelper
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private lateinit var job: Job


    private lateinit var mViewDataBinding: binding
    private lateinit var mViewModel: viewmodel
    val binding: binding get() = mViewDataBinding
    val viewModel: viewmodel get() = mViewModel

    protected abstract fun getViewModelClass(): Class<viewmodel>

    protected abstract fun getLayoutId(): binding

    protected abstract fun onViewReady(savedInstance: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job = Job()
        mViewModel =  ViewModelProvider(this)[getViewModelClass()]
        mViewDataBinding = getLayoutId()
        onViewReady(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

}