package tk.lorddarthart.rxnewstestapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JSONPlaceHolderApi {
    @GET("/uc")
    Call<News> getPostWithID(@Query(value = "id", encoded = true) String id);
}
