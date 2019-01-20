package tk.lorddarthart.rxnewstestapp;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpServiceHelper {
    private static HttpServiceHelper mInstance;
    private static final String BASE_URL = "https://drive.google.com/uc?id=1wozWr5swgtdV9PLyo2b09mtjaOD6sS2I";
    private Retrofit mRetrofit;

    private HttpServiceHelper() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .addInterceptor(interceptor);
        
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static HttpServiceHelper getInstance() {
        if (mInstance == null) {
            mInstance = new HttpServiceHelper();
        }
        return mInstance;
    }

    public JSONPlaceHolderApi getJSONApi() {
        return mRetrofit.create(JSONPlaceHolderApi.class);
    }
}
