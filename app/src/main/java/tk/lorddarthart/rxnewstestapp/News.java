package tk.lorddarthart.rxnewstestapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;

import java.util.List;

public class News {
    @SerializedName("News")
    @Expose
    private List<Item> news;

    List<Item> getNews() {
        return news;
    }

    public void setNews(List<Item> news) {
        this.news = news;
    }
}
