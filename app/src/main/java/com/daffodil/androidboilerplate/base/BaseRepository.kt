package com.daffodil.androidboilerplate.base

import com.daffodil.androidboilerplate.network.ApiService

abstract class BaseRepository {
    abstract fun apiInterface(): ApiService
}