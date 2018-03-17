package ir.beigirad.zeroapplication.utils

import android.content.Context
import android.content.SharedPreferences
import ir.beigirad.zeroapplication.ZeroApplication

/**
 * Created by farhad-mbp on 7/26/17.
 */

class SharedPrefUtils {
    private val KEY1 = "KEY1"

    private var userPreferences: SharedPreferences
    private var mContext: Context = ZeroApplication.getAppContext()

    init {
        userPreferences = mContext.getSharedPreferences("UserSettings", Context.MODE_PRIVATE)
    }

    var password: String
        get() = userPreferences.getString(KEY1, "")
        set(value) {
            userPreferences.edit().putString(KEY1, value).apply()
        }
}