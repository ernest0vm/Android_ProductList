package mx.ernestovaldez.androidtest.models

import com.google.gson.annotations.SerializedName

data class Status (

	@SerializedName("status") val status : String,
	@SerializedName("statusCode") val statusCode : Int
)