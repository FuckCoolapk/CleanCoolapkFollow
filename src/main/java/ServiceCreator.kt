import com.lz233.onetext.tools.utils.getAS
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {
    private const val baseURL = "https://api.coolapk.com/"
    private val okHttpClient = OkHttpClient().newBuilder().addInterceptor {
        val request = it.request().newBuilder()
            .addHeader("User-Agent","Dalvik/2.1.0 (Linux; U; Android 1; MIX Alpha BadAppleOS/20.1.21) (#Build; Apple; iPhone 12 Pro Max; QKQ1.190828.002 test-keys; 10) +CoolMarket/11.0.1-2102031-universal")
            .addHeader("X-Requested-With","XMLHttpRequest")
            .addHeader("X-Sdk-Int","29")
            .addHeader("X-Sdk-Locale","zh-CN")
            .addHeader("X-App-Id","com.coolapk.market")
            .addHeader("X-App-Token", getAS())
            .addHeader("X-App-Version","11.0.1")
            .addHeader("X-App-Code","2102031")
            //TODO: 更改 header
            .addHeader("X-App-Device","AbsVnb7EUMxAzRgsTZsd2bvdGI7UGbn92bnByOEVjO4MkO1gjO3MjO3IjOyEDI7AyOgsjMhhTYxcTO1MWNzUTZjZGM")
            .addHeader("X-Dark-Mode","0")
            .addHeader("X-App-Channel","coolapk")
            .addHeader("X-App-Mode","universal")
            .addHeader("Cookie","uid=${CoolapkConfig.uid}; username=${CoolapkConfig.userName}; token=${CoolapkConfig.token}")
            .build()
        it.proceed(request)
    }.build()
    private val retrofit = Retrofit.Builder()
        .baseUrl(baseURL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)
}