package com.masic.bwallet.main.fragments.wallet


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListAdapter

import com.masic.bwallet.R
import kotlinx.android.synthetic.main.fragment_wallet.*


/**
 * A simple [Fragment] subclass.
 */
class WalletFragment : Fragment(), WalletContract.View {
    private var presenter: WalletContract.Presenter? = null

    companion object {
        fun newInstance(): WalletFragment {
            val fragment = WalletFragment()
            return fragment
        }
    }

    override fun displayAvailableBalance(balance: String) {
        tv_wallet_available_balance.post {
            tv_wallet_available_balance.text = balance
        }
    }

    override fun displayBalance(balance: String) {
        tv_wallet_account_balance.post {
            tv_wallet_account_balance.text = balance
        }
    }

    override fun displayEstimatedBalance(balance: String) {
        tv_wallet_estimated_balance.post {
            tv_wallet_estimated_balance.text = balance
        }
    }

    override fun displayTransactions(transactions: List<String>) {
        if (!transactions.isEmpty()) {
            displayTransactionsLabel(true)
            val array = transactions.toTypedArray()
            val adapter: ListAdapter = ArrayAdapter<String>(this.context, android.R.layout.simple_list_item_1, array)
            lv_wallet_transactions.adapter = adapter
        }
    }

    override fun displayTransactionsLabel(display: Boolean) {
        tv_transactions_label.post {
            tv_transactions_label.visibility = if (display) View.VISIBLE else View.INVISIBLE
        }
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_wallet, container, false)
    }

    override fun onStart() {
        super.onStart()
        initData()
        initUi()
        presenter?.viewIsReady()
    }


    private fun initData() {
        presenter = WalletPresenter(this)
    }

    private fun initUi() {
        displayTransactionsLabel(false)
        srl_tx_refresher.setOnRefreshListener {
            presenter?.refresh()
            srl_tx_refresher.isRefreshing = false
            srl_tx_refresher.isEnabled = true
        }
    }
}// Required empty public constructor
