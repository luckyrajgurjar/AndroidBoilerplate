package com.daffodil.androidboilerplate.viewmodel

import com.daffodil.androidboilerplate.base.BaseViewModel
import com.daffodil.androidboilerplate.data.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository
): BaseViewModel() {

}
