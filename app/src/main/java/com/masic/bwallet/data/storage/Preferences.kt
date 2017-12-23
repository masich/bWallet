package com.masic.bwallet.data.storage

import android.content.Context
import android.content.SharedPreferences
import android.R.id.edit


/**
 * Created by masic on 20.12.2017
 * Project: bWallet
 */
class Preferences {
    private val preferences: SharedPreferences

    constructor(context: Context) {
        preferences = context.getSharedPreferences(FILE_NAME, 0)
    }

    val FILE_NAME = "preferences"

    val PREF_WALLET = "wallet_available"

    private fun getEditor(): SharedPreferences.Editor {
        return preferences.edit()
    }

    fun setWalletAvailable(data: Boolean) {
        getEditor().putBoolean(PREF_WALLET, data).commit()
    }

    fun isWalletAvailable(): Boolean {
        return preferences.getBoolean(PREF_WALLET, false)
    }
}