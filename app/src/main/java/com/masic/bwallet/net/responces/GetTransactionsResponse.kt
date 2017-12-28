package net.responces

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import net.models.BitcoinTransactionModel

/**
 * Created by masic_0 on 26.12.2017.
 * Project name: Wallet
 */
class GetTransactionsResponse {
    @SerializedName("items")
    @Expose
    var items: List<BitcoinTransactionModel>? = null

    @SerializedName("totalItems")
    @Expose
    var totalItems: Long? = null
}