package com.masic.bwallet.data.api

import android.graphics.Bitmap
import org.bitcoinj.core.Address
import org.bitcoinj.core.Coin
import org.bitcoinj.core.Transaction
import org.bitcoinj.core.listeners.DownloadProgressTracker
import org.bitcoinj.wallet.DeterministicSeed
import org.bitcoinj.wallet.WalletTransaction
import java.io.File

/**
 * Created by masic on 20.12.2017
 * Project: bWallet
 */
interface ApiHelper {
    fun getBalance(): Coin
    fun getAvailableBalance(): Coin
    fun getEstimatedBalance(): Coin
    fun getReceiveAddress(): Address
    fun getWalletTransactions(): List<Transaction>
    fun getMnemonicCode(): DeterministicSeed
    fun getAllUsedAddresses(): List<Address>
    fun restoreWalletFromSeed(mnemonicCode: String)
    fun setDownloadListenerToKit(listener: DownloadProgressTracker)
    fun loadOrCreateNewWallet()
    fun closeWallet()
    fun getAddressQrCodeBitmap(width: Int, height: Int): Bitmap
    fun sendCoins(amount: String, destinationBase58Address: String)
}