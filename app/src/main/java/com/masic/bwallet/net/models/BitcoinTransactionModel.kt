package net.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BitcoinTransactionModel {

    @SerializedName("txid")
    @Expose
    var txid: String? = null
    @SerializedName("version")
    @Expose
    var version: Int? = null
    @SerializedName("locktime")
    @Expose
    var locktime: Int? = null
    @SerializedName("vin")
    @Expose
    var vin: List<BitcoinVinModel>? = null
    @SerializedName("vout")
    @Expose
    var vout: List<BitcoinVoutModel>? = null
    @SerializedName("blockhash")
    @Expose
    var blockhash: String? = null
    @SerializedName("confirmations")
    @Expose
    var confirmations: Int? = null
    @SerializedName("time")
    @Expose
    var time: Int? = null
    @SerializedName("blocktime")
    @Expose
    var blocktime: Int? = null
    @SerializedName("valueOut")
    @Expose
    var valueOut: Double? = null
    @SerializedName("size")
    @Expose
    var size: Int? = null
    @SerializedName("firstSeenTs")
    @Expose
    var firstSeenTs: String? = null
    @SerializedName("valueIn")
    @Expose
    var valueIn: Double? = null
    @SerializedName("fees")
    @Expose
    var fees: Double? = null

    override fun toString(): String {
        return "net.models.BitcoinTransactionModel(txid=$txid, version=$version, locktime=$locktime, vin=$vin, vout=$vout, blockhash=$blockhash, confirmations=$confirmations, time=$time, blocktime=$blocktime, valueOut=$valueOut, size=$size, firstSeenTs=$firstSeenTs, valueIn=$valueIn, fees=$fees)"
    }
}