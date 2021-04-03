package com.taymiyyah.sample
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


interface DateApi {
    companion object {
        const val CONVERT_END_POINT = "gToHCalendar/"
    }

    @GET("$CONVERT_END_POINT{date}")
    suspend fun convertFromGDateTOH(
        @Path("date") date: String
    ): Response<HashMap<Any,Any>>
}


fun getRetrofit(): Retrofit {
    val interceptor = HttpLoggingInterceptor()
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    val client: OkHttpClient =OkHttpClient().newBuilder(). addInterceptor(interceptor).build()

    return Retrofit.Builder()
        .baseUrl("http://api.aladhan.com/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
}

fun getDateApi(retrofit: Retrofit= getRetrofit()): DateApi =
    retrofit.create(DateApi::class.java)
