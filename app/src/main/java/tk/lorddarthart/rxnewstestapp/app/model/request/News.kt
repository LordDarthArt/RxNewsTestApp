package tk.lorddarthart.rxnewstestapp.app.model.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import tk.lorddarthart.rxnewstestapp.app.model.request.Item

class News {
    @SerializedName("News")
    @Expose
    internal var news: List<Item>? = null
        set
}
