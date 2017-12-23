package com.masic.bwallet.main

import com.masic.bwallet.base.PresenterBase
import com.masic.bwallet.main.fragments.State

/**
 * Created by masic on 20.12.2017
 * Project: bWallet
 */
class MainPresenter private constructor() : PresenterBase<MainContract.View>(), MainContract.Presenter {
    private lateinit var model: MainContract.Model
    private var state: State = State.NOT_DEFINED

    constructor(view: MainContract.View) : this() {
        setView(view)
        setModel(MainModel())

    }

    override fun setModel(mvpModel: MainContract.Model) {
        model = mvpModel
    }

    override fun destroy() {
    }

    override fun onWallet() {
        if (state != State.WALLET) {
            state = State.WALLET
            getView()?.showWalletFragment()
        }
    }

    override fun onReceive() {
        if (state != State.RECEIVE) {
            state = State.RECEIVE
            getView()?.showReceiveFragment()
        }
    }

    override fun onTransfer() {
        if (state != State.TRANSFER) {
            state = State.TRANSFER
            getView()?.showTransferFragment()

        }
    }
}