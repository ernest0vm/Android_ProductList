package mx.ernestovaldez.androidtest.models

import com.google.gson.annotations.SerializedName

data class Refinement (

	@SerializedName("count") val count : Int,
	@SerializedName("label") val label : String,
	@SerializedName("refinementId") val refinementId : String,
	@SerializedName("selected") val selected : Boolean
)