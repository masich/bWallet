package com.masic.bwallet.main.fragments.transfer

import android.content.Context
import com.google.zxing.integration.android.IntentResult
import com.masic.bwallet.base.MvpPresenter
import com.masic.bwallet.base.MvpView

/**
 * Created by masic on 21.12.2017
 * Project: bWallet
 */
interface TransferContract {
    interface View : MvpView {
        fun getDestinationAddress(): String
        fun getAmount(): String
        fun setDestinationAddress(address: String)
        fun getContext(): Context
    }

    interface Presenter : MvpPresenter<View> {
        fun onConfirmTransfer()
        fun onQrCodeScanned(scanResult: IntentResult?)
    }
}