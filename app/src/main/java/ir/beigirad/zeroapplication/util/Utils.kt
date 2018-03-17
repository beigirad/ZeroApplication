package ir.beigirad.zeroapplication.util

import android.Manifest
import android.annotation.TargetApi
import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Point
import android.graphics.Typeface
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.VectorDrawable
import android.hardware.fingerprint.FingerprintManager
import android.os.Build
import android.support.annotation.DrawableRes
import android.support.design.widget.TabLayout
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.support.graphics.drawable.VectorDrawableCompat
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat
import android.support.v7.widget.AppCompatImageView
import android.text.InputType
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import ir.beigirad.app.BuildConfig
import ir.beigirad.app.R
import ir.beigirad.zeroapplication.ZeroApplication
import ir.beigirad.zeroapplication.model.DeviceInfo



/**
 * Created by farhad-mbp on 9/11/17.
 */

class Utils {
    companion object {
        fun validateView(et: EditText, isRequired: Boolean): Boolean {
            return if (isRequired) validateView(et, null) else if ("" == et.text.toString().trim { it <= ' ' }) true else validateView(et, null)
        }

        private fun validateView(et: EditText, til: TextInputLayout? = null): Boolean {
            val text = et.text.toString().trim { it <= ' ' }
            var isValid = true
            var error = ""

            when (et.inputType) {
                (InputType.TYPE_NUMBER_VARIATION_PASSWORD + InputType.TYPE_CLASS_NUMBER),
                InputType.TYPE_TEXT_VARIATION_PASSWORD -> if (text.length < 4) {
                    error = ZeroApplication.getAppContext().resources.getString(R.string.password_wrong)
                    isValid = false
                }
                InputType.TYPE_CLASS_PHONE -> if (!text.matches("09\\w+".toRegex()) || text.length != 11) {
                    error = ZeroApplication.getAppContext().resources.getString(R.string.phone_not_valid)
                    isValid = false
                }
                InputType.TYPE_TEXT_VARIATION_PERSON_NAME -> if (text.length < 3) {
                    error = ZeroApplication.getAppContext().resources.getString(R.string.input_not_valid)
                    isValid = false
                }

                InputType.TYPE_TEXT_VARIATION_WEB_EMAIL_ADDRESS or InputType.TYPE_CLASS_TEXT, InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS or InputType.TYPE_CLASS_TEXT -> {
                    val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
                    if (!text.matches(emailPattern.toRegex())) {
                        error = ZeroApplication.getAppContext().resources.getString(R.string.input_not_valid)
                        isValid = false
                    }
                    if (text.length < 3) {
                        error = ZeroApplication.getAppContext().resources.getString(R.string.input_not_valid)
                        isValid = false
                    }
                }

                else -> if (text.length < 3) {
                    error = ZeroApplication.getAppContext().resources.getString(R.string.input_not_valid)
                    isValid = false
                }
            }
            if (isValid) {
                if (til != null)
                    til.isErrorEnabled = false
                return true
            } else {
                if (til != null)
                    til.error = error
                else
                    et.error = error
                et.requestFocus()
                return false
            }


        }

        fun validatePass(et: EditText, til: TextInputLayout): Boolean {
            return validatePass(et, til, et, til)
        }

        fun validatePass(et: TextInputEditText, til: TextInputLayout): Boolean {
            return validatePass(et, til, et, til)
        }

        fun validatePass(et: EditText, til: TextInputLayout? = null, et_repeat: EditText, til_repeat: TextInputLayout? = null): Boolean {
            val text = et.text.toString().trim { it <= ' ' }
            val text_repeat = et_repeat.text.toString().trim { it <= ' ' }


            if (!validateView(et, til))
                return false
            if (!validateView(et_repeat, til_repeat))
                return false
            val error = ZeroApplication.getAppContext().resources.getString(R.string.password_not_same)
            if (text != text_repeat) {
                if (til != null)
                    til_repeat!!.error = error
                else
                    et_repeat.error = error

                et_repeat.requestFocus()
                return false
            }
            if (til != null)
                til.isErrorEnabled = false
            //        else
            //            et.setError("");
            return true
        }

        fun validatePass(et: TextInputEditText, til: TextInputLayout? = null, et_repeat: TextInputEditText, til_repeat: TextInputLayout? = null): Boolean {
            val text = et.text.toString().trim { it <= ' ' }
            val text_repeat = et_repeat.text.toString().trim { it <= ' ' }


            if (!validateView(et, til))
                return false
            if (!validateView(et_repeat, til_repeat))
                return false
            val error = ZeroApplication.getAppContext().resources.getString(R.string.password_not_same)
            if (text != text_repeat) {
                if (til != null)
                    til_repeat!!.error = error
                else
                    et_repeat.error = error

                et_repeat.requestFocus()
                return false
            }
            if (til != null)
                til.isErrorEnabled = false
            //        else
            //            et.setError("");
            return true
        }

        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        private fun getBitmap(vectorDrawable: VectorDrawable): Bitmap {
            val bitmap = Bitmap.createBitmap(vectorDrawable.intrinsicWidth,
                    vectorDrawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            vectorDrawable.setBounds(0, 0, canvas.width, canvas.height)
            vectorDrawable.draw(canvas)
            return bitmap
        }

        private fun getBitmap(vectorDrawable: VectorDrawableCompat): Bitmap {
            val bitmap = Bitmap.createBitmap(vectorDrawable.intrinsicWidth,
                    vectorDrawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            vectorDrawable.setBounds(0, 0, canvas.width, canvas.height)
            vectorDrawable.draw(canvas)
            return bitmap
        }

        fun getBitmap(context: Context, @DrawableRes drawableResId: Int): Bitmap {
            val drawable = ContextCompat.getDrawable(context, drawableResId)
            return when (drawable) {
                is BitmapDrawable -> drawable.bitmap
                is VectorDrawableCompat -> getBitmap(drawable)
                is VectorDrawable -> getBitmap(drawable)
                else -> throw IllegalArgumentException("Unsupported drawable type")
            }
        }

        fun getDrawable(context: Context, @DrawableRes drawableResId: Int): Drawable {
            val img = AppCompatImageView(context)
            img.setImageResource(drawableResId)
            //        return ContextCompat.getDrawable(context, drawableResId);
            return img.drawable
        }

        val deviceInfo: DeviceInfo
            get() {
                val manufacturer = Build.MANUFACTURER
                val model = Build.MODEL
                val sdk = Build.VERSION.SDK_INT
                val appVersionName = BuildConfig.VERSION_NAME
                val appVersionCode = BuildConfig.VERSION_CODE
                return DeviceInfo(manufacturer, model, sdk, appVersionName, appVersionCode, "")
            }

//        fun glideOption(): RequestOptions {
//            return RequestOptions().placeholder(R.mipmap.placeholder)
//                    .error(R.mipmap.placeholder_error)
//                    .dontAnimate()
//        }

        val isTablet: Boolean
            get() = ZeroApplication.getAppContext().resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK >= Configuration.SCREENLAYOUT_SIZE_LARGE


        fun getDate(formatedDate: String): String {
            return formatedDate.substring(0, formatedDate.indexOf('T')).replace('-', '/')
        }

        fun isFingerSensorAvialable(): Boolean {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                ActivityCompat.checkSelfPermission(ZeroApplication.getAppContext(),
                        Manifest.permission.USE_FINGERPRINT) == PackageManager.PERMISSION_GRANTED &&
                        ZeroApplication.getAppContext().getSystemService(FingerprintManager::class.java).isHardwareDetected &&
                        ZeroApplication.getAppContext().getSystemService(FingerprintManager::class.java).hasEnrolledFingerprints()

            } else {
                FingerprintManagerCompat.from(ZeroApplication.getAppContext()).isHardwareDetected
            }
        }

        fun showReport(message: String) {
            if (BuildConfig.DEBUG)
                showMsg(message)
        }

        fun showReport(message: Int) {
            if (BuildConfig.DEBUG)
                showMsg(message)
        }

        fun showMsg(message: String) {
//            var toast = Toast(ZeroApplication.getAppContext())
//            val textView = TextView(ZeroApplication.getAppContext())
//            textView.setText(message)
//            textView.typeface = ZeroApplication.getTypeface()
//            textView.background = ContextCompat.getDrawable(ZeroApplication.getAppContext(), (R.drawable.background_solid))
//            toast.view = textView
//            toast.show()
            Toast.makeText(ZeroApplication.getAppContext(), message, Toast.LENGTH_SHORT).show()
        }

        fun showMsg(message: Int) {
            Toast.makeText(ZeroApplication.getAppContext(), message, Toast.LENGTH_SHORT).show()
        }

        fun changeTabLayoutFont(tabLayout: TabLayout, typeface: Typeface) {
            val vg = tabLayout.getChildAt(0) as ViewGroup
            (0 until vg.childCount)
                    .map { vg.getChildAt(it) as ViewGroup }
                    .forEach { vgTab ->
                        (0 until vgTab.childCount)
                                .asSequence()
                                .map { vgTab.getChildAt(it) as View }
                                .filterIsInstance<TextView>()
                                .forEach { it.typeface = typeface }
                    }
        }

        fun openSoftKeyboard(context: Context) {
            val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
        }

        fun closeSoftKeyboard(context: Context) {
            val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, InputMethodManager.RESULT_UNCHANGED_SHOWN);
        }

        fun copyOnClipboard(text: String, label: String = "") {
            var clipboard = ZeroApplication.getAppContext().getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
            var clip = ClipData.newPlainText(label, text)
            clipboard?.primaryClip = clip
        }

        public fun convertDpToPixel(dp: Float, context: Context): Int {
            var resources = context.resources
            var metrics = resources.displayMetrics
            var px = dp * (metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT)
            return px.toInt()
        }

        public fun convertPixelsToDp(px: Float, context: Context): Float {
            var resources = context.resources
            var metrics = resources.displayMetrics
            var dp = px / (metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT)
            return dp
        }

        public fun getScreenWidth(activity: Activity): Int {
            var size = Point()
            activity.windowManager.defaultDisplay.getSize(size)
            return size.x
        }
    }
}
