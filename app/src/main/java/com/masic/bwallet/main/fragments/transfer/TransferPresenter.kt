package com.masic.bwallet.main.fragments.transfer

import android.util.Log
import com.google.zxing.integration.android.IntentResult
import com.masic.bwallet.base.PresenterBase
import com.masic.bwallet.data.api.BitcoinApiHelper
import org.bitcoinj.core.AddressFormatException
import org.bitcoinj.core.InsufficientMoneyException
import org.bitcoinj.core.WrongNetworkException
import org.jetbrains.anko.*

/**
 * Created by masic on 21.12.2017
 * Project: bWallet
 */
class TransferPresenter private constructor() : PresenterBase<TransferContract.View>(), TransferContract.Presenter {
    val TAG = this.javaClass.simpleName

    constructor(mvpView: TransferContract.View) : this() {
        setView(mvpView)
    }

    override fun destroy() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onConfirmTransfer() {
        val amount = getView()?.getAmount()
        val destinationAddress = getView()?.getDestinationAddress()
        if (!(amount.isNullOrEmpty() || destinationAddress.isNullOrEmpty())) {
            try {
                BitcoinApiHelper.instance.sendCoins(amount!!, destinationAddress!!)
            } catch (e: InsufficientMoneyException) {
                getView()?.getContext()?.toast("Missing ${e.missing!!.getValue()}")
            } catch (e: AddressFormatException) {
                getView()?.getContext()?.toast("Invalid address")
            } catch (e: WrongNetworkException) {
                getView()?.getContext()?.toast("Wrong address network")
            } catch (e: IllegalArgumentException) {
                getView()?.getContext()?.toast("Wrong amount")
            } catch (e: NumberFormatException) {
                getView()?.getContext()?.toast("Wrong amount")
            }
        }

    }

    override fun onQrCodeScanned(scanResult: IntentResult?) {
        Log.d(TAG, "qrCodeScanned()")
        if (scanResult != null) {
            Log.d(TAG, "qrCodeScanned(): address: ${scanResult.contents}")
            getView()?.setDestinationAddress(scanResult.contents)
        }
    }
}