package tk.lorddarthart.rxnewstestapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JSONPlaceHolderApi {
    @GET("/posts/{id}")
    Call<News> getPostWithID(@Path("id") int id);
}
