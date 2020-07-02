package mx.ernestovaldez.androidtest.models

import com.google.gson.annotations.SerializedName

data class ApiResponse (

	@SerializedName("status") val status : Status,
	@SerializedName("pageType") val pageType : String,
	@SerializedName("plpResults") val plpResults : PlpResults
)