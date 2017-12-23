package com.masic.bwallet.main.fragments.wallet

import com.masic.bwallet.base.MvpPresenter
import com.masic.bwallet.base.MvpView

/**
 * Created by masic on 20.12.2017
 * Project: bWallet
 */
interface WalletContract {
    interface View : MvpView {
        fun displayAvailableBalance(balance: String)
        fun displayBalance(balance: String)
        fun displayEstimatedBalance(balance: String)
        fun displayTransactions(transactions: List<String>)
        fun displayTransactionsLabel(display: Boolean)
    }

    interface Presenter : MvpPresenter<View> {
        fun getModel(): Model
        fun setModel(mvpModel: Model)
        fun refresh()
        fun onTransactionListChanged(newTransactions: List<String>)
        fun setBalance(newBalance: String)
        fun setAvailableBalance(newBalance: String)
        fun setEstimatedBalance(newBalance: String)
    }

    interface Model {
        fun refresh()
        fun getTransactionList(): List<String>
        fun getBalance(): String
        fun getAvailableBalance(): String
        fun getEstimatedBalance(): String
    }
}