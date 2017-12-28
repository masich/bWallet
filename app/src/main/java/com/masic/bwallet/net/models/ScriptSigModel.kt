package net.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ScriptSigModel {

    @SerializedName("asm")
    @Expose
    var asm: String? = null
    @SerializedName("hex")
    @Expose
    var hex: String? = null

}