package com.masic.bwallet.main.fragments.wallet

import com.masic.bwallet.base.PresenterBase

/**
 * Created by masic on 20.12.2017
 * Project: bWallet
 */
class WalletPresenter() : PresenterBase<WalletContract.View>(), WalletContract.Presenter {
    private lateinit var model: WalletContract.Model

    constructor(view: WalletContract.View) : this() {
        setView(view)
        setModel(WalletModel(this))
    }

    constructor(view: WalletContract.View, model: WalletContract.Model) : this() {
        setView(view)
        setModel(model)
    }

    override fun destroy() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun viewIsReady() {
        getView()?.displayTransactions(model.getTransactionList())
        getView()?.displayBalance(model.getBalance())
        getView()?.displayAvailableBalance(model.getAvailableBalance())
        getView()?.displayEstimatedBalance(model.getEstimatedBalance())
    }

    override fun onTransactionListChanged(newTransactions: List<String>) {
        getView()?.displayTransactions(newTransactions)
    }

    override fun setBalance(newBalance: String) {
        getView()?.displayBalance(newBalance)
    }

    override fun setAvailableBalance(newBalance: String) {
        getView()?.displayAvailableBalance(newBalance)
    }

    override fun setEstimatedBalance(newBalance: String) {
        getView()?.displayEstimatedBalance(newBalance)
    }

    override fun refresh() {
        viewIsReady()
    }

    override fun setModel(mvpModel: WalletContract.Model) {
        model = mvpModel
    }

    override fun getModel(): WalletContract.Model = model

}