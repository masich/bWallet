package com.masic.bwallet.settings

import com.masic.bwallet.StartActivity
import com.masic.bwallet.base.PresenterBase
import com.masic.bwallet.data.Repository
import com.masic.bwallet.data.api.BitcoinApiHelper
import com.masic.bwallet.login.LoginActivity

/**
 * Created by masic on 22.12.2017
 * Project: bWallet
 */
class SettingsPresenter private constructor() : PresenterBase<SettingsContract.View>(), SettingsContract.Presenter {
    constructor(mvpView: SettingsContract.View) : this() {
        setView(mvpView)
    }

    override fun onLogOut() {
        BitcoinApiHelper.deinit()
        Repository.instance.setWalletOnStorage(false)
        getView()?.next(StartActivity::class.java)
    }

    override fun destroy() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setAllOfUsedAddresses() {
        val addresses = BitcoinApiHelper.instance.getAllUsedAddresses()
        if (!addresses.isEmpty()) {
            val newAddresses = ArrayList<String>()
            addresses.forEach { newAddresses.add(it.toBase58()) }
            getView()?.displayAddressesLabel(true)
            getView()?.displayAllOfUsedAddresses(newAddresses)
        } else {
            getView()?.displayAddressesLabel(false)
        }
    }

    override fun viewIsReady() {
        setAllOfUsedAddresses()
    }
}