package com.masic.bwallet.main.fragments.wallet

import com.masic.bwallet.data.api.BitcoinApiHelper

/**
 * Created by masic on 20.12.2017
 * Project: bWallet
 */
class WalletModel(val presenter: WalletContract.Presenter) : WalletContract.Model {
    override fun getTransactionList(): List<String> {
        val list = ArrayList<String>()
        BitcoinApiHelper.instance.getWalletTransactions()
                .forEach { list.add(it.hashAsString) }
        return list
    }

    override fun getBalance() = BitcoinApiHelper.instance.getBalance().toFriendlyString()!!

    override fun getAvailableBalance() = BitcoinApiHelper.instance.getAvailableBalance().toFriendlyString()!!

    override fun getEstimatedBalance() = BitcoinApiHelper.instance.getEstimatedBalance().toFriendlyString()!!

    override fun refresh() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}