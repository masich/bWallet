package com.masic.bwallet.data.api

import android.content.Context
import android.util.Log
import net.glxn.qrgen.android.QRCode
import org.bitcoinj.core.Address
import org.bitcoinj.core.Coin
import org.bitcoinj.core.InsufficientMoneyException
import org.bitcoinj.core.Transaction
import org.bitcoinj.core.listeners.DownloadProgressTracker
import org.bitcoinj.kits.WalletAppKit
import org.bitcoinj.wallet.DeterministicSeed
import org.bitcoinj.wallet.Wallet
import java.io.File

/**
 * Created by masic on 20.12.2017
 * Project: bWallet
 */
class BitcoinApiHelper private constructor(var kit: WalletAppKit) : ApiHelper {
    val TAG = this.javaClass.simpleName
    var walletLoaded: Boolean = false
        private set

    companion object {
        lateinit var instance: BitcoinApiHelper
        var isCreated = false
            private set

        fun init(context: Context) {
            if (!isCreated) {
                instance = BitcoinApiHelper(WalletAppKit(DEFAULT_NET_PARAMS.get(), File(context.cacheDir, DEFAULT_WALLET_KIT_DIR), DEFAULT_WALLET_NAME))
                isCreated = !isCreated
            }
        }

        fun deinit() {
            instance.closeWallet()
            instance.deleteWalletKitFiles()
        }
    }


    override fun getBalance() = kit.wallet()?.balance!!

    override fun getAvailableBalance() = kit.wallet()?.getBalance(Wallet.BalanceType.AVAILABLE_SPENDABLE)!!

    override fun getEstimatedBalance() = kit.wallet().getBalance(Wallet.BalanceType.ESTIMATED_SPENDABLE)!!

    override fun getReceiveAddress() = kit.wallet().currentReceiveAddress()!!

    override fun getWalletTransactions(): List<Transaction> = kit.wallet().transactionsByTime!!

    override fun getMnemonicCode() = kit.wallet().keyChainSeed!!

    override fun getAllUsedAddresses(): MutableList<Address> = kit.wallet().issuedReceiveAddresses!!

    override fun loadOrCreateNewWallet() {
        Log.d(TAG, "create or load wallet")
        if (!kit.isRunning) {
            ThreadHelper.setBtcSDKThread()
            kit.setBlockingStartup(false)
            kit.startAsync()
            walletLoaded = true
        }

    }

    override fun restoreWalletFromSeed(mnemonicCode: String) {
        Log.d(TAG, "restore wallet from seed")
        if (!kit.isRunning) {
            ThreadHelper.setBtcSDKThread()
            val determSeed = DeterministicSeed(mnemonicCode, null, DEFAULT_PASSPHRASE, DEFAULT_WALLET_RESTORE_TIME)
            kit.restoreWalletFromSeed(determSeed)
            kit.setBlockingStartup(false)
            kit.startAsync()
            walletLoaded = true
        }

    }

    override fun setDownloadListenerToKit(listener: DownloadProgressTracker) {
        kit.setDownloadListener(listener)
    }

    override fun getAddressQrCodeBitmap(width: Int, height: Int) =
            QRCode.from(getReceiveAddress().toBase58()!!).withSize(width, height).bitmap()!!

    //Todo: rewrite fun because it useless
    override fun closeWallet() {
        kit.stopAsync()
        kit.awaitTerminated()
    }

    private fun deleteWalletKitFiles() {
        kit.directory().deleteRecursively()
    }

    /**
     * @throws InsufficientMoneyException if not enough coins in wallet to perform transaction
     */
    override fun sendCoins(amount: String, destinationBase58Address: String) {
        Log.d(TAG, "sendCoins(): amount $amount ; to $destinationBase58Address")
        val value = Coin.parseCoin(amount)
        val to = Address.fromBase58(kit.params(), destinationBase58Address)
        val result = kit.wallet().sendCoins(kit.peerGroup(), to, value)
        Log.d(TAG, "sendCoins(): Coins sent. TX hash: ${result.tx.hashAsString}")
    }
}