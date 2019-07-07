package tk.lorddarthart.rxnewstestapp.application.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Item {
    @SerializedName("tittle")
    @Expose
    internal var title: String? = null
    @SerializedName("desc")
    @Expose
    internal var desc: String? = null
    @SerializedName("date")
    @Expose
    internal var date: String? = null
    @SerializedName("pic")
    @Expose
    internal var pic: String? = null
    @SerializedName("link")
    @Expose
    internal var link: String? = null
}
