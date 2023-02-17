package com.daffodil.androidboilerplate.data.dao

import androidx.room.*

@Dao
interface BaseDao<T>  {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(data: ArrayList<T>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: T)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateList(data: List<T>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(data: T)

    @Delete
    suspend fun delete(data: T)
}