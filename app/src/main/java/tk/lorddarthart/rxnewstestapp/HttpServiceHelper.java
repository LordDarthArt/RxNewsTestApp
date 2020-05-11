package tk.lorddarthart.rxnewstestapp;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class HttpServiceHelper {
    private static HttpServiceHelper mInstance;
    private static final String BASE_URL = "http://10.0.2.2:8080";
    private Retrofit mRetrofit;

    private HttpServiceHelper() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor).build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    static HttpServiceHelper getInstance() {
        if (mInstance == null) {
            mInstance = new HttpServiceHelper();
        }
        return mInstance;
    }

    JSONPlaceHolderApi getJSONApi() {
        return mRetrofit.create(JSONPlaceHolderApi.class);
    }
}
