package mx.ernestovaldez.androidtest.models

import com.google.gson.annotations.SerializedName

data class VariantsColor (

	@SerializedName("colorName") val colorName : String,
	@SerializedName("colorHex") val colorHex : String,
	@SerializedName("colorImageURL") val colorImageURL : String
)