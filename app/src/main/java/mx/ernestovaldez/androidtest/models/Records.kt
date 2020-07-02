package mx.ernestovaldez.androidtest.models

import com.google.gson.annotations.SerializedName

data class Records (

	@SerializedName("productId") val productId : Int,
	@SerializedName("skuRepositoryId") val skuRepositoryId : Int,
	@SerializedName("productDisplayName") val productDisplayName : String,
	@SerializedName("productType") val productType : String,
	@SerializedName("productRatingCount") val productRatingCount : Int,
	@SerializedName("productAvgRating") val productAvgRating : Double,
	@SerializedName("listPrice") val listPrice : Double,
	@SerializedName("minimumListPrice") val minimumListPrice : Double,
	@SerializedName("maximumListPrice") val maximumListPrice : Double,
	@SerializedName("promoPrice") val promoPrice : Double,
	@SerializedName("minimumPromoPrice") val minimumPromoPrice : Double,
	@SerializedName("maximumPromoPrice") val maximumPromoPrice : Double,
	@SerializedName("isHybrid") val isHybrid : Boolean,
	@SerializedName("marketplaceSLMessage") val marketplaceSLMessage : String,
	@SerializedName("marketplaceBTMessage") val marketplaceBTMessage : String,
	@SerializedName("isMarketPlace") val isMarketPlace : Boolean,
	@SerializedName("isImportationProduct") val isImportationProduct : Boolean,
	@SerializedName("smImage") val smImage : String,
	@SerializedName("lgImage") val lgImage : String,
	@SerializedName("xlImage") val xlImage : String,
	@SerializedName("groupType") val groupType : String,
	@SerializedName("plpFlags") val plpFlags : List<String>,
	@SerializedName("variantsColor") val variantsColor : List<VariantsColor>
)