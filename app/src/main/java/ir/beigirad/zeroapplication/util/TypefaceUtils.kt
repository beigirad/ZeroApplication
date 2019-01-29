package ir.beigirad.zeroapplication.util

import android.content.Context
import android.graphics.Typeface
import android.os.Build
import timber.log.Timber


// https://gist.github.com/artem-zinnatullin/7749076
object TypefaceUtils {

    /**
     * Using reflection to override default typeface
     * NOTICE: DO NOT FORGET TO SET TYPEFACE FOR APP THEME AS DEFAULT TYPEFACE WHICH WILL BE OVERRIDDEN
     */
    fun overrideFont(defaultFontNameToOverride: String, face: Typeface) {
        try {
            val defaultFontTypefaceField =
                Typeface::class.java.getDeclaredField(defaultFontNameToOverride)
            defaultFontTypefaceField.isAccessible = true
            defaultFontTypefaceField.set(null, face)
        } catch (e: Exception) {
            Timber.e("Can not set custom font $face instead of $defaultFontNameToOverride")
        }
    }

    fun getAppFont(context: Context): Typeface {
        return Typeface.createFromAsset(context.assets, "")
    }

    fun overrideFont(
        context: Context,
        defaultFontNameToOverride: String,
        customFontFileNameInAssets: String
    ) {

        val customFontTypeface =
            Typeface.createFromAsset(context.assets, customFontFileNameInAssets)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val newMap = HashMap<String, Typeface>()
            newMap["serif"] = customFontTypeface
            try {
                val staticField = Typeface::class.java
                    .getDeclaredField("sSystemFontMap")
                staticField.isAccessible = true
                staticField.set(null, newMap)
            } catch (e: NoSuchFieldException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            }

        } else {
            try {
                val defaultFontTypefaceField =
                    Typeface::class.java.getDeclaredField(defaultFontNameToOverride)
                defaultFontTypefaceField.isAccessible = true
                defaultFontTypefaceField.set(null, customFontTypeface)
            } catch (e: Exception) {
                Timber.e(
                    "Can not set custom font $customFontFileNameInAssets instead of $defaultFontNameToOverride"
                )
            }

        }
    }
}