package tk.lorddarthart.rxnewstestapp.util.network.retrofit

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import tk.lorddarthart.rxnewstestapp.util.constants.Constants.BASE_URL
import com.google.gson.GsonBuilder
import com.google.gson.Gson



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
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build()
            }
            return ourInstance!!
        }
    }
}