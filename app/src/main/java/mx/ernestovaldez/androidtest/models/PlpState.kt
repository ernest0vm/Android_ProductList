package mx.ernestovaldez.androidtest.models

import com.google.gson.annotations.SerializedName

data class PlpState (

	@SerializedName("categoryId") val categoryId : String,
	@SerializedName("currentSortOption") val currentSortOption : String,
	@SerializedName("currentFilters") val currentFilters : String,
	@SerializedName("firstRecNum") val firstRecNum : Int,
	@SerializedName("lastRecNum") val lastRecNum : Int,
	@SerializedName("recsPerPage") val recsPerPage : Int,
	@SerializedName("totalNumRecs") val totalNumRecs : Int
)