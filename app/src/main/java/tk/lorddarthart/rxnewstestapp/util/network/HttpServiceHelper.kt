package tk.lorddarthart.rxnewstestapp.util.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tk.lorddarthart.rxnewstestapp.util.constants.Constants.BASE_URL
import tk.lorddarthart.rxnewstestapp.util.network.retrofit.GetNewsList

internal class HttpServiceHelper private constructor() {
    private val retrofit: Retrofit
    private val client: OkHttpClient

    private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()

    val newsApi: GetNewsList
        get() = retrofit.create(GetNewsList::class.java)

    init {
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        client = OkHttpClient.Builder()
                .addInterceptor(interceptor).build()

        retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapter.create())
                .client(client)
                .build()
    }

    companion object {
        private val TAG = HttpServiceHelper::class.java.simpleName

        lateinit var instance: HttpServiceHelper
    }
}
