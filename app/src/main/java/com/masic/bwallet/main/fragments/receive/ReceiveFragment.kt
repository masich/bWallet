package com.masic.bwallet.main.fragments.receive


import android.graphics.Bitmap
import android.os.Binder
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.masic.bwallet.R
import kotlinx.android.synthetic.main.fragment_receive.*
import org.jetbrains.anko.image
import org.jetbrains.anko.imageBitmap


/**
 * A simple [Fragment] subclass.
 */
class ReceiveFragment : Fragment(), ReceiveContract.View {
    private var presenter: ReceiveContract.Presenter? = null


    override fun displayWalletReceiveAddress(address: String) {
        tv_user_receive_address_value.text = address
    }

    override fun displayWalletReceiveAddressQrCode(qrCode: Bitmap) {
        iv_user_qr_code.imageBitmap = qrCode
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_receive, container, false)
    }

    companion object {
        fun newInstance(): ReceiveFragment {
            val fragment = ReceiveFragment()
            return fragment
        }
    }

    override fun onStart() {
        super.onStart()
        initData()
        presenter?.viewIsReady()
    }

    private fun initData() {
        presenter = ReceivePresenter(this)
    }
}// Required empty public constructor
