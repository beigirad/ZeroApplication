package ir.beigirad.zeroapplication.network.model

import com.google.gson.annotations.SerializedName

data class APIError(

        @field:SerializedName("status_title")
        val message: String = "",
        @field:SerializedName("status_id")
        val status_id: Long = 500,
        @field:SerializedName("Message")
        val status_title: String = ""

)