package ir.beigirad.zeroapplication.model

import com.google.gson.annotations.SerializedName

data class DeviceInfo(

        @SerializedName("manufacture")
        var manufacture: String? = null,

        @SerializedName("model")
        var model: String? = null,

        @SerializedName("sdk")
        var sdk: Int = 0,

        @SerializedName("app_version_name")
        var appVersionName: String? = null,

        @SerializedName("app_version_code")
        var appVersionCode: Int,

        @SerializedName("firebase_token")
        var firebaseToken: String? = null
)