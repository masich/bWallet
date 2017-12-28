package net.responces

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BlockCountResponse {

    @SerializedName("blockcount")
    @Expose
    var blockcount: Long? = null
}
