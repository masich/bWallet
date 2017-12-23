package com.masic.bwallet.login

import android.content.Context
import com.masic.bwallet.base.MvpPresenter
import com.masic.bwallet.base.MvpView
import com.masic.bwallet.data.api.NetworkParams
import com.masic.bwallet.main.MainActivity
import java.io.File

/**
 * Created by masic on 20.12.2017
 * Project: bWallet
 */


interface LoginContract {
    interface View : MvpView {
        fun displayProgressBar(show: Boolean)

        //Get mnemonic code from edit text
        fun getMnemonicCode(): String

        //Show message to user
        fun showMessageToUser(messageResId: Int)

        //set download progress
        fun setDownloadProgress(progress: Int)

        // go to next screen
        fun next(cls: Class<MainActivity>)

        // Get Context cache dir
        fun getWalletDir(): File

        // close screen
        fun close()

        fun getAppContext(): Context
    }

    interface Presenter : MvpPresenter<View> {
        fun setModel(mvpModel: Model)
        fun getModel(): Model
        fun onConfirm()
        fun onCreateOrLoadWallet()
        fun onChainDownloaded(downloaded: Boolean)
        fun onDownloadProgressChanged(progress: Int)
        fun getAppContext(): Context
    }

    interface Model {
        fun loadOrCreateWallet()
        fun destroy()
        fun restoreWalletFromSeed(mnemonicCode: String)
    }
}