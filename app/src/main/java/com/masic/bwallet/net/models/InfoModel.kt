package net.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class InfoModel {

    @SerializedName("version")
    @Expose
    var version: Int? = null
    @SerializedName("protocolversion")
    @Expose
    var protocolversion: Int? = null
    @SerializedName("blocks")
    @Expose
    var blocks: Int? = null
    @SerializedName("timeoffset")
    @Expose
    var timeoffset: Int? = null
    @SerializedName("connections")
    @Expose
    var connections: Int? = null
    @SerializedName("proxy")
    @Expose
    var proxy: String? = null
    @SerializedName("difficulty")
    @Expose
    var difficulty: Double? = null
    @SerializedName("testnet")
    @Expose
    var testnet: Boolean? = null
    @SerializedName("relayfee")
    @Expose
    var relayfee: Double? = null
    @SerializedName("errors")
    @Expose
    var errors: String? = null
    @SerializedName("network")
    @Expose
    var network: String? = null

}
