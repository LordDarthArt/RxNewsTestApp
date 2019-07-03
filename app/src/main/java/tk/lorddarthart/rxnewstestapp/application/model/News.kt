package tk.lorddarthart.rxnewstestapp.application.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class News {
    @SerializedName("News")
    @Expose
    internal var news: List<Item>? = null
        set
}
