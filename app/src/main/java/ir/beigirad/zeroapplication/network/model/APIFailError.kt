package ir.beigirad.zeroapplication.network.model

import com.google.gson.annotations.SerializedName

data class APIFailError(
        @field:SerializedName("message")
        val message: String = "null message",

        @field:SerializedName("cause")
        val cause: String? = null

)