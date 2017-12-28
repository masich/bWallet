package net

import org.bitcoinj.core.Address
import org.bitcoinj.params.TestNet3Params
import org.bitcoinj.wallet.DeterministicSeed
import org.bitcoinj.wallet.KeyChain
import org.bitcoinj.wallet.KeyChainGroup

/**
 * Created by masic_0 on 28.12.2017.
 * Project name: Wallet
 */
fun main(args: Array<String>) {
//    println(BitcoinApiHelper.getNetworkInfo()?.blocks)
//    println(BitcoinApiHelper.getTransactions(listOf(Address.fromBase58(TestNet3Params.get(),"mvdfyxbJFXB8iN3pvpyBE9Ukw8JpUidyyP"),
//            Address.fromBase58(TestNet3Params.get(), "mwE7F3uPWHMnZuV66yXbPPxyPP4Fy6o2nk")))?.size)
//    println(BitcoinApiHelper.getUTXOs(listOf(Address.fromBase58(TestNet3Params.get(),"mvdfyxbJFXB8iN3pvpyBE9Ukw8JpUidyyP"),
//            Address.fromBase58(TestNet3Params.get(), "mwE7F3uPWHMnZuV66yXbPPxyPP4Fy6o2nk")))?.size)
//    println(BitcoinApiHelper.getEstimatedFee())

    var seed = DeterministicSeed(listOf("team", "earn", "demise", "glad", "thrive", "arena", "path", "stairs", "brave", "update", "orient", "gain"),
            null, "", 100000000000)

    var kcg = KeyChainGroup(TestNet3Params.get(), seed)
//    println(kcg.activeKeyChain.getKeys(KeyChain.KeyPurpose.RECEIVE_FUNDS, 20).joinToString(transform = {it.toAddress(TestNet3Params.get()).toBase58()}))
//    println(kcg.activeKeyChain.getKeys(KeyChain.KeyPurpose.CHANGE, 20).joinToString(transform = {it.toAddress(TestNet3Params.get()).toBase58()}))

    var usedKeys = BitcoinApiHelper.getUsedAddresses(KeyChain.KeyPurpose.CHANGE, kcg.activeKeyChain)
   // println(usedKeys.size)
    var addresses = mutableListOf<Address>()
    usedKeys.forEach { addresses.add(it.toAddress(TestNet3Params.get())) }
    var txs = BitcoinApiHelper.getTransactions(addresses)
    println(txs?.size)

}