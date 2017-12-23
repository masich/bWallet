package com.masic.bwallet.main.fragments.receive

import android.graphics.Bitmap
import android.location.Address
import com.masic.bwallet.base.MvpPresenter
import com.masic.bwallet.base.MvpView

/**
 * Created by masic on 20.12.2017
 * Project: bWallet
 */
interface ReceiveContract {
    interface View : MvpView {
        fun displayWalletReceiveAddress(address: String)
        fun displayWalletReceiveAddressQrCode(qrCode: Bitmap)
    }

    interface Presenter : MvpPresenter<View> {
        fun setWalletRecentAddress()
        fun setWalletReceiveAddressQrCode()
        fun refresh()
    }
}