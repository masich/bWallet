package com.masic.bwallet.main.fragments.receive

import com.masic.bwallet.base.PresenterBase
import com.masic.bwallet.data.api.BitcoinApiHelper
import com.masic.bwallet.data.api.DEFAULT_QR_CODE_IMAGE_SIZE

/**
 * Created by masic on 21.12.2017
 * Project: bWallet
 */
class ReceivePresenter private constructor() : PresenterBase<ReceiveContract.View>(), ReceiveContract.Presenter {
    constructor(view: ReceiveContract.View) : this() {
        setView(view)
    }

    override fun destroy() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setWalletRecentAddress() {
        getView()?.displayWalletReceiveAddress(BitcoinApiHelper.instance.getReceiveAddress().toBase58()!!)
    }

    override fun viewIsReady() {
        refresh()
    }

    override fun setWalletReceiveAddressQrCode() {
        val qrCode = BitcoinApiHelper.instance.getAddressQrCodeBitmap(DEFAULT_QR_CODE_IMAGE_SIZE, DEFAULT_QR_CODE_IMAGE_SIZE)
        getView()?.displayWalletReceiveAddressQrCode(qrCode)
    }

    override fun refresh() {
        setWalletRecentAddress()
        setWalletReceiveAddressQrCode()
    }
}