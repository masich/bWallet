package com.masic.bwallet.login

import android.util.Log
import com.masic.bwallet.data.api.*
import org.bitcoinj.core.listeners.DownloadProgressTracker
import java.util.*

/**
 * Created by masic on 20.12.2017
 * Project: bWallet
 */
class LoginModel : LoginContract.Model {
    constructor(mvpPresenter: LoginContract.Presenter) {
        presenter = mvpPresenter
        BitcoinApiHelper.init(presenter.getAppContext())
    }

    val TAG = this.javaClass.simpleName
    var presenter: LoginContract.Presenter

    override fun loadOrCreateWallet() {
        setDownloadListenerToKit()
        BitcoinApiHelper.instance.loadOrCreateNewWallet()
    }

    override fun restoreWalletFromSeed(mnemonicCode: String) {
        setDownloadListenerToKit()
        BitcoinApiHelper.instance.restoreWalletFromSeed(mnemonicCode)
    }

    override fun destroy() {
    }

    private fun setDownloadListenerToKit() {
        BitcoinApiHelper.instance.setDownloadListenerToKit(object : DownloadProgressTracker() {
            override fun progress(pct: Double, blocksSoFar: Int, date: Date?) {
                super.progress(pct, blocksSoFar, date)
                Log.d(TAG, "download progress $pct")
                presenter.onDownloadProgressChanged(pct.toInt())
            }

            override fun startDownload(blocks: Int) {
                super.startDownload(blocks)
                Log.d(TAG, "startDownload()")
                presenter.onChainDownloaded(false)

            }

            override fun doneDownload() {
                super.doneDownload()
                Log.d(TAG, "doneDownload()")
                presenter.onChainDownloaded(true)
            }
        })
    }
}