package tk.lorddarthart.rxnewstestapp.util.network.retrofit

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import tk.lorddarthart.rxnewstestapp.util.constants.Constants.BASE_URL


class RetrofitClient {
    constructor() {

    }

    companion object {
        private var ourInstance: Retrofit? = null
        const val TAG = "RetrofitClient"

        fun getInstance(): Retrofit {
            if (ourInstance == null) {
                val gson = GsonBuilder()
                        .setLenient()
                        .create()

                ourInstance = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build()
            }
            return ourInstance!!
        }
    }
}