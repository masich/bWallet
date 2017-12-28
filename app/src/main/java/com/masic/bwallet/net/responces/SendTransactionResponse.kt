package net.responces

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by masic_0 on 27.12.2017.
 * Project name: Wallet
 */
class SendTransactionResponse {

    @SerializedName("txid")
    @Expose
    var txid: String? = null

}
