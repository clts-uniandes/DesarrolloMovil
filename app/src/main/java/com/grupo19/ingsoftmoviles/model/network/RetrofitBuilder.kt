package com.grupo19.ingsoftmoviles.model.network

import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitBuilder {

    private val BASE_URL = "http://34.130.203.231:3000"

    fun<T> createClient(clazz: Class<T>, baseUrl: String = BASE_URL): T {
        val mHttpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

        val mOkHttpClient = OkHttpClient
            .Builder()
            .addNetworkInterceptor(CacheInterceptor())
            .addInterceptor(mHttpLoggingInterceptor)
            //.addInterceptor(ForceCacheInterceptor())
            .build()

        val retrofit =  Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(mOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(clazz)
    }

    class CacheInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val response: Response = chain.proceed(chain.request())
            val cacheControl = CacheControl.Builder()
                .maxAge(6, TimeUnit.HOURS)
                .build()
            return response.newBuilder()
                .header("Cache-Control", cacheControl.toString())
                .build()
        }
    }

    /*class ForceCacheInterceptor : Interceptor { // if no internet available
        override fun intercept(chain: Interceptor.Chain): Response {
            val builder : Request.Builder = chain.request().newBuilder()
            if (!isInternetAvailable() {
                builder.cacheControl(CacheControl.FORCE_CACHE);
            }
            return chain.proceed(builder.build())
        }

        private fun isInternetAvailable(context: Context): Boolean {
            var isConnected: Boolean = false // Initial Value
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
            if (activeNetwork != null && activeNetwork.isConnected)
                isConnected = true
            return isConnected
        }
    }*/

}