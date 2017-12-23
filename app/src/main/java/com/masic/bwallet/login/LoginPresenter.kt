package com.masic.bwallet.login

import android.content.Context
import android.util.Log
import com.masic.bwallet.base.PresenterBase
import com.masic.bwallet.data.Repository
import com.masic.bwallet.data.api.DEFAULT_WALLET_NAME
import com.masic.bwallet.data.api.NetworkParams
import com.masic.bwallet.data.storage.Preferences
import com.masic.bwallet.main.MainActivity

/**
 * Created by masic on 20.12.2017
 * Project: bWallet
 */
class LoginPresenter() : PresenterBase<LoginContract.View>(), LoginContract.Presenter {
    val TAG = this.javaClass.simpleName
    private var model: LoginContract.Model? = null

    constructor(view: LoginContract.View, model: LoginContract.Model) : this() {
        setView(view)
        setModel(model)
    }

    constructor(view: LoginContract.View) : this() {
        setView(view)
        setModel(LoginModel(this))
    }

    override fun onDownloadProgressChanged(progress: Int) {
        getView()?.setDownloadProgress(progress)
    }

    override fun onChainDownloaded(downloaded: Boolean) {
        getView()?.displayProgressBar(!downloaded)
        if (downloaded) {
            Repository.instance.setWalletOnStorage(downloaded)
            getView()?.next(MainActivity::class.java)
            getView()?.close()
            detachView()
            destroy()
        }
    }

    override fun setModel(mvpModel: LoginContract.Model) {
        model = mvpModel
    }

    override fun getModel() = model!!

    override fun destroy() {
        model?.destroy()
        model = null
    }

    override fun onConfirm() {
        val mnemonicCode = getView()?.getMnemonicCode()
        // if (isValidCodeData(mnemonicCode!!)) {
        model?.restoreWalletFromSeed(mnemonicCode!!)
        Log.d(TAG, mnemonicCode)
//        } else {
//            //TODO: show error message to user
//        }
    }

    override fun onCreateOrLoadWallet() {
        getView()?.displayProgressBar(true)
        model?.loadOrCreateWallet()
    }

    override fun getAppContext() = getView()?.getAppContext()!!
}