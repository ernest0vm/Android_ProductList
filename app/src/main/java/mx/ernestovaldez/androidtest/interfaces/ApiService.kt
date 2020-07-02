package mx.ernestovaldez.androidtest.interfaces
import mx.ernestovaldez.androidtest.models.ApiResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("plp")
    fun getDataFromApi(
        @Query("force-plp") forcePlp: Boolean,
        @Query("search-string") searchString: String,
        @Query("page-number") pageNumber: Int,
        @Query("number-of-items-per-page") itemsPerPage: Int
    ): Call<ApiResponse>

}