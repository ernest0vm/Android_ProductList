package mx.ernestovaldez.androidtest.managers

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiManager {

    fun getApiService(): Retrofit {
        val baseUrl = "https://shoppapp.liverpool.com.mx/appclienteservices/services/v3/"

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}