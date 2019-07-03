package tk.lorddarthart.rxnewstestapp.util

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import tk.lorddarthart.rxnewstestapp.application.model.News

interface JSONPlaceHolderApi {
    @GET("/uc")
    fun getPostWithID(@Query(value = "id", encoded = true) id: String): Call<News>
}
