package net.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BitcoinVinModel {

    @SerializedName("txid")
    @Expose
    var txid: String? = null
    @SerializedName("vout")
    @Expose
    var vout: Int? = null
    @SerializedName("scriptSig")
    @Expose
    var scriptSig: ScriptSigModel? = null
    @SerializedName("sequence")
    @Expose
    var sequence: Long? = null
    @SerializedName("n")
    @Expose
    var n: Int? = null
    @SerializedName("addr")
    @Expose
    var addr: String? = null
    @SerializedName("valueSat")
    @Expose
    var valueSat: Long? = null
    @SerializedName("value")
    @Expose
    var value: Double? = null
    @SerializedName("doubleSpentTxID")
    @Expose
    var doubleSpentTxID: Any? = null

}


