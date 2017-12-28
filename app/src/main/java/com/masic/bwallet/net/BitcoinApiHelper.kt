package net

import net.models.BitcoinTransactionModel
import net.models.InfoModel
import net.models.UtxoModel
import net.responces.GetTransactionsResponse
import net.responces.InfoResponce
import net.responces.SendTransactionResponse
import org.bitcoinj.core.Address
import org.bitcoinj.core.Transaction
import org.bitcoinj.crypto.DeterministicKey
import org.bitcoinj.params.TestNet3Params
import org.bitcoinj.wallet.DeterministicKeyChain
import org.bitcoinj.wallet.KeyChain
import org.spongycastle.util.encoders.Hex
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by masic_0 on 28.12.2017.
 * Project name: Wallet
 */
object BitcoinApiHelper {
    private lateinit var retrofit: Retrofit
    private lateinit var api: BitcoinTestNetApi

    init {
        retrofit = Retrofit.Builder()
                .baseUrl("https://testnet.blockexplorer.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        api = retrofit.create(BitcoinTestNetApi::class.java)
    }

    fun getNetworkInfo(): InfoModel? {
        val call = api.getNetworkInfo()
        val lock = java.lang.Object()
        var info: InfoModel? = null
        call.enqueue(object : Callback<InfoResponce> {
            override fun onFailure(call: Call<InfoResponce>?, t: Throwable?) {
                t?.printStackTrace()
                synchronized(lock) {
                    lock.notifyAll()
                }
            }

            override fun onResponse(call: Call<InfoResponce>?, response: Response<InfoResponce>?) {
                synchronized(lock) {
                    info = response?.body()?.info
                    lock.notifyAll()
                }
            }

        })
        synchronized(lock) {
            lock.wait()
            return info
        }
    }

    fun getTransactions(addresses: List<Address>): List<BitcoinTransactionModel>? {
        val arrdressesStr = addresses.joinToString(transform = { it.toBase58() })
        val lock = java.lang.Object()
        var transactions = mutableListOf<BitcoinTransactionModel>()
        var from = 0
        var to = 20
        var totalItems: Long = 20
        var call = api.getTxsForAddresses(arrdressesStr, from, to)
//        call.enqueue(object : Callback<GetTransactionsResponse> {
//            override fun onFailure(call: Call<GetTransactionsResponse>?, t: Throwable?) {
//                t?.printStackTrace()
//                synchronized(lock) {
//                    lock.notifyAll()
//                }
//            }
//
//            override fun onResponse(call: Call<GetTransactionsResponse>?, response: Response<GetTransactionsResponse>?) {
//                synchronized(lock) {
//                    var totalItems = response?.body()?.totalItems!!
//                    transactions.addAll(response?.body()?.items!!)
//                    println(totalItems)
//                    from += 20
//                    to += 20
//
//                }
//            }
//        })
        do {
            var txResponce = call.execute()
            transactions.addAll(txResponce?.body()?.items!!)
            totalItems = txResponce.body()?.totalItems!!
            from += 20
            to += 20
            call = api.getTxsForAddresses(arrdressesStr, from, to)
        } while (from < totalItems)
        return transactions
    }

    fun getUTXOs(addresses: List<Address>): List<UtxoModel>? {
        val arrdressesStr = addresses.joinToString(transform = { it.toBase58() })
        val call = api.getUtxoForAddresses(arrdressesStr)
        val lock = java.lang.Object()
        var utxos: List<UtxoModel>? = null
        call.enqueue(object : Callback<List<UtxoModel>> {
            override fun onFailure(call: Call<List<UtxoModel>>?, t: Throwable?) {
                t?.printStackTrace()
                synchronized(lock) {
                    lock.notifyAll()
                }
            }

            override fun onResponse(call: Call<List<UtxoModel>>?, response: Response<List<UtxoModel>>?) {
                synchronized(lock) {
                    utxos = response?.body()
                    lock.notifyAll()
                }
            }
        })
        synchronized(lock) {
            lock.wait()
            return utxos
        }
    }

    /**
     * @returns: a txid
     */
    fun sentTx(tx: Transaction): String? {
        val hexTx = Hex.toHexString(tx.unsafeBitcoinSerialize())
        val call = api.sendTx(hexTx)
        val lock = java.lang.Object()
        var txid: String? = null
        call.enqueue(object : Callback<SendTransactionResponse> {
            override fun onFailure(call: Call<SendTransactionResponse>?, t: Throwable?) {
                t?.printStackTrace()
                synchronized(lock) {
                    lock.notifyAll()
                }
            }

            override fun onResponse(call: Call<SendTransactionResponse>?, response: Response<SendTransactionResponse>?) {
                synchronized(lock) {
                    txid = response?.body()?.txid
                    lock.notifyAll()
                }
            }
        })
        synchronized(lock) {
            lock.wait()
            return txid
        }
    }


    fun getUsedAddresses(purpose: KeyChain.KeyPurpose, keyChain: DeterministicKeyChain): MutableList<DeterministicKey> {
        var addressIndex = 0
        var lastUsedIndex = 0
        var listOfKeys = keyChain.getKeys(purpose, 20)
        var listOfAddresses = mutableListOf<Address>()
        listOfKeys.forEach { listOfAddresses.add(it.toAddress(TestNet3Params.get())) }
        var listOfTxs = getTransactions(listOfAddresses)
        while (listOfTxs!!.isNotEmpty()) {
            //println("txs" + listOfTxs.size)
            while (addressIndex < listOfAddresses.size) {
                if (isUsedAddressInTxs(listOfAddresses[addressIndex], listOfTxs)) {
                    lastUsedIndex++
                }
                addressIndex++
            }
            var countNeedAddresses = lastUsedIndex % 20
            println(countNeedAddresses)
            var listNewKeys = keyChain.getKeys(purpose, countNeedAddresses)
            listOfAddresses = mutableListOf<Address>()
            listNewKeys.forEach { listOfAddresses.add(it.toAddress(TestNet3Params.get())) }
            listOfTxs = getTransactions(listOfAddresses)
            println("txs" + listOfTxs?.size)
            addressIndex = 0
            listOfKeys.addAll(listNewKeys)

        }
        //println(lastUsedIndex)
        return listOfKeys.subList(0, lastUsedIndex + 1)
    }

    fun isUsedAddressInTxs(address: Address, transacrions: List<BitcoinTransactionModel>): Boolean {
        for (btm in transacrions) {
            for (vout in btm.vout!!) {
                if (address.toBase58() in vout.scriptPubKey?.addresses!!) {
                    return true
                }
            }
            for (vin in btm.vin!!) {
                if (vin.addr.equals(address.toBase58())) {
                    return true
                }
            }
        }
        return false
    }

}