package tk.lorddarthart.rxnewstestapp.util.network.retrofit

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import tk.lorddarthart.rxnewstestapp.application.model.News

interface GetNewsList {
    @GET("/uc")
    fun getPostWithID(@Query(value = "id", encoded = true) id: String): Observable<News>
}
