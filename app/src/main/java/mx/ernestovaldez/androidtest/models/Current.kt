package mx.ernestovaldez.androidtest.models

import com.google.gson.annotations.SerializedName

data class Current (

	@SerializedName("label") val label : String,
	@SerializedName("categoryId") val categoryId : String
)