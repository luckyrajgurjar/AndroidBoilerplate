package com.daffodil.androidboilerplate.data

import android.app.Application
import com.daffodil.androidboilerplate.base.BaseRepository
import com.daffodil.androidboilerplate.network.ApiService
import com.daffodil.androidboilerplate.network.NetworkReachability
import com.daffodil.androidboilerplate.network.ServiceManager
import javax.inject.Inject

class MainRepository  @Inject constructor(
    private val app: Application,

    ) :BaseRepository()  {
    override fun apiInterface(): ApiService {
        return ServiceManager().get().createService(
            ApiService::class.java,
            NetworkReachability(app), null
        )
    }
    val database = AppDatabase.getInstance(app)
}