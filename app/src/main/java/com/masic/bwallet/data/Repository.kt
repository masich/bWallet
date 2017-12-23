package com.masic.bwallet.data

import android.content.Context
import com.masic.bwallet.data.storage.Preferences
import org.bitcoinj.kits.WalletAppKit
import org.bitcoinj.wallet.Wallet

/**
 * Created by masic on 20.12.2017
 * Project: bWallet
 */
class Repository private constructor(var prefs: Preferences) {
    companion object {
        lateinit var instance: Repository
        fun init(context: Context) {
            instance = Repository(Preferences(context))
        }
    }

    fun isWalletOnStorage() = prefs.isWalletAvailable()
    fun setWalletOnStorage(value: Boolean) {
        prefs.setWalletAvailable(value)
    }
}