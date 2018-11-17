package ir.beigirad.zeroapplication

import android.app.Application
import android.content.Context
import android.graphics.Typeface
import ir.beigirad.app.R

/**
 * Created by farhad-mbp on 11/4/17.
 */

open class ZeroApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        mContext = applicationContext

    }

    companion object {
        private var mContext: Context? = null
        private var mTypeface: Typeface? = null
        private var mTypefaceBold: Typeface? = null


        fun getAppContext(): Context {
            return mContext!!
        }

        fun getTypeface(): Typeface {
            return mTypeface.let {
                mTypeface = Typeface.createFromAsset(getAppContext().assets, getAppContext().getString(R.string.font_regular))
                mTypeface!!
            }
        }

        fun getBoldTypeface(): Typeface {
            return mTypefaceBold.let {
                mTypefaceBold = Typeface.createFromAsset(getAppContext().assets, getAppContext().getString(R.string.font_bold))
                mTypefaceBold!!
            }
        }
    }
}
