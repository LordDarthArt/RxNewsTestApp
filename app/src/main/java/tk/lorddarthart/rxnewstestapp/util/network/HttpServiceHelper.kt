package tk.lorddarthart.rxnewstestapp.util.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tk.lorddarthart.rxnewstestapp.util.JSONPlaceHolderApi

internal class HttpServiceHelper private constructor() {
    private val mRetrofit: Retrofit

    val jsonApi: JSONPlaceHolderApi
        get() = mRetrofit.create(JSONPlaceHolderApi::class.java)

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)

        mRetrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    companion object {
        private var mInstance: HttpServiceHelper? = null
        private val BASE_URL = "https://drive.google.com/"

        val instance: HttpServiceHelper
            get() {
                if (mInstance == null) {
                    mInstance = HttpServiceHelper()
                }
                return mInstance!!
            }
    }
}
