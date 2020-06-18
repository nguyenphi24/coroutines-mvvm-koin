package vn.phitn.app.coroutines.model

import com.google.gson.annotations.SerializedName

/*
* Created by phitn on 6/19/2020
*/

data class Cat(
    @SerializedName("id")
    val id: String,
    @SerializedName("url")
    val imageUrl: String
)