package com.masic.bwallet.main

import com.masic.bwallet.base.MvpPresenter
import com.masic.bwallet.base.MvpView

/**
 * Created by masic on 20.12.2017
 * Project: bWallet
 */
interface MainContract {
    interface View : MvpView {
        fun showReceiveFragment()
        fun showTransferFragment()
        fun showWalletFragment()
    }

    interface Presenter : MvpPresenter<View> {
        fun onWallet()
        fun onReceive()
        fun onTransfer()
        fun setModel(mvpModel: MainContract.Model)
    }

    interface Model {
        fun onCloseWallet()
    }
}