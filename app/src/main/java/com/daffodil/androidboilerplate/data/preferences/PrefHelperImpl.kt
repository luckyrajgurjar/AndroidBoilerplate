package com.daffodil.androidboilerplate.data.preferences

import android.app.Application
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.daffodil.androidboilerplate.DemoApplication
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PrefHelper @Inject constructor(ctx: Application) {

    val PREF_FILE_NAME = "boilerplate_prefs"
    private var masterKey = MasterKey.Builder(ctx, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private var mPrefs = EncryptedSharedPreferences.create(
        ctx,
        PREF_FILE_NAME,
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )
    private val editor = mPrefs.edit()

    fun getPrefsEditor() = editor

/*

    override fun saveToString(prefName: String, prefValue: String) {
        editor.putString(prefName, prefValue)
        editor.apply()
    }

    override fun saveToBoolean(prefName: String, prefValue: Boolean) {
        editor.putBoolean(prefName, prefValue)
        editor.apply()
    }

    override fun saveToInteger(prefName: String, prefValue: Int) {
        editor.putInt(prefName, prefValue)
        editor.apply()
    }

    override fun readString(prefName: String, defValue: String): String? =
        mPrefs.getString(prefName, defValue)

    override fun readBoolean(prefName: String, defValue: Boolean): Boolean =
        mPrefs.getBoolean(prefName, defValue)

    override fun readInteger(prefName: String, defValue: Int): Int =
        mPrefs.getInt(prefName, defValue)

    override fun readDate(prefName: String, defValue: Long): Long =
        mPrefs.getLong(prefName, defValue)


    override fun saveToDate(prefName: String, prefValue: Long) {

        editor.putLong(prefName, prefValue)
        editor.apply()
    }

*/

}
