package com.masic.bwallet.settings

import com.masic.bwallet.StartActivity
import com.masic.bwallet.base.MvpPresenter
import com.masic.bwallet.base.MvpView
import com.masic.bwallet.login.LoginActivity

/**
 * Created by masic on 22.12.2017
 * Project: bWallet
 */
interface SettingsContract {
    interface View : MvpView {
        fun displayAllOfUsedAddresses(addresses: List<String>)
        fun displayAddressesLabel(display: Boolean)
        fun next(cls: Class<StartActivity>)
    }

    interface Presenter : MvpPresenter<View> {
        fun onLogOut()
        fun setAllOfUsedAddresses()
    }

    interface Model {
        fun logOut()
    }
}