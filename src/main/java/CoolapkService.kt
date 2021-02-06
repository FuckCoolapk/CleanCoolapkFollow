import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.*

interface CoolapkService {

    @GET("v6/user/space")
    fun getUserDetail(@Query("uid") uid: String): Call<JsonObject>

    @GET("v6/user/followList")
    fun getFollowList(@Query("uid") uid: String, @Query("page") page: String): Call<JsonObject>

    @POST("v6/user/unfollow")
    fun unFollow(@Query("uid") fUid: String): Call<JsonObject>
}