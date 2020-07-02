package mx.ernestovaldez.androidtest.models

import com.google.gson.annotations.SerializedName
import mx.ernestovaldez.androidtest.models.Refinement

data class RefinementGroups (

    @SerializedName("name") val name : String,
    @SerializedName("refinement") val refinement : List<Refinement>,
    @SerializedName("multiSelect") val multiSelect : Boolean,
    @SerializedName("dimensionName") val dimensionName : String
)