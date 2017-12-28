package net.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BitcoinVoutModel {

    @SerializedName("value")
    @Expose
    var value: String? = null
    @SerializedName("n")
    @Expose
    var n: Int? = null
    @SerializedName("scriptPubKey")
    @Expose
    var scriptPubKey: ScriptPubKeyModel? = null
    @SerializedName("spentTxId")
    @Expose
    var spentTxId: String? = null
    @SerializedName("spentIndex")
    @Expose
    var spentIndex: Int? = null
    @SerializedName("spentHeight")
    @Expose
    var spentHeight: Int? = null

}

