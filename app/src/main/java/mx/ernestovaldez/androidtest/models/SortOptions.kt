package mx.ernestovaldez.androidtest.models

import com.google.gson.annotations.SerializedName

data class SortOptions (

	@SerializedName("sortBy") val sortBy : String,
	@SerializedName("label") val label : String
)