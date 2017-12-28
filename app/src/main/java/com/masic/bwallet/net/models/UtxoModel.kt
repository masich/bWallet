package net.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UtxoModel {

    @SerializedName("address")
    @Expose
    var address: String? = null
    @SerializedName("txid")
    @Expose
    var txid: String? = null
    @SerializedName("vout")
    @Expose
    var vout: Int? = null
    @SerializedName("scriptPubKey")
    @Expose
    var scriptPubKey: String? = null
    @SerializedName("amount")
    @Expose
    var amount: Double? = null
    @SerializedName("satoshis")
    @Expose
    var satoshis: Long? = null
    @SerializedName("height")
    @Expose
    var height: Int? = null
    @SerializedName("confirmations")
    @Expose
    var confirmations: Int? = null

}