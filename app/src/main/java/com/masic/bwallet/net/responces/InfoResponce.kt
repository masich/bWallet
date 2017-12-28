package net.responces

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import net.models.InfoModel


class InfoResponce {
    @SerializedName("info")
    @Expose
    var info: InfoModel? = null
}