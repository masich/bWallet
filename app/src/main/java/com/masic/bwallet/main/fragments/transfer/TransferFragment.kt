package com.masic.bwallet.main.fragments.transfer


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.masic.bwallet.R
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.fragment_transfer.*


/**
 * A simple [Fragment] subclass.
 */
class TransferFragment : Fragment(), TransferContract.View {
    val TAG = this.javaClass.simpleName
    private var presenter: TransferContract.Presenter? = null

    override fun getDestinationAddress() = et_destination_address.text.toString()

    override fun getAmount() = et_transfer_amount.text.toString()

    override fun setDestinationAddress(address: String) {
        et_destination_address.setText(address)
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_transfer, container, false)
    }

    companion object {
        fun newInstance(): TransferFragment {
            val fragment = TransferFragment()
            return fragment
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d(TAG, "qrCodeScanned()")
        val scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        presenter?.onQrCodeScanned(scanResult)
    }

    override fun onStart() {
        super.onStart()
        initData()
        initUi()
        presenter?.viewIsReady()
    }

    private fun initUi() {
        iv_destination_qr_code.setOnClickListener {
            val integrator = IntentIntegrator(activity)
            integrator.setOrientationLocked(false)
            integrator.initiateScan()
        }
        b_confirm_transfer_button.setOnClickListener {
            presenter?.onConfirmTransfer()
        }
    }

    private fun initData() {
        presenter = TransferPresenter(this)
    }


}
