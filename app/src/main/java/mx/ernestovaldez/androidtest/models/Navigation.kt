package mx.ernestovaldez.androidtest.models

import com.google.gson.annotations.SerializedName
import mx.ernestovaldez.androidtest.models.Current

data class Navigation (

    @SerializedName("ancester") val ancester : List<String>,
    @SerializedName("current") val current : List<Current>,
    @SerializedName("childs") val childs : List<String>
)