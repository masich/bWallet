package net.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ScriptPubKeyModel {

    @SerializedName("hex")
    @Expose
    var hex: String? = null
    @SerializedName("asm")
    @Expose
    var asm: String? = null
    @SerializedName("addresses")
    @Expose
    var addresses: List<String>? = null
    @SerializedName("type")
    @Expose
    var type: String? = null

}