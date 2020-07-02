package mx.ernestovaldez.androidtest.models

import com.google.gson.annotations.SerializedName

data class PlpResults (

	@SerializedName("label") val label : String,
	@SerializedName("plpState") val plpState : PlpState,
	@SerializedName("sortOptions") val sortOptions : List<SortOptions>,
	@SerializedName("refinementGroups") val refinementGroups : List<RefinementGroups>,
	@SerializedName("records") val records : List<Records>,
	@SerializedName("navigation") val navigation : Navigation
)