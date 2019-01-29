package ir.beigirad.zeroapplication

import android.app.Application
import android.content.Context
import timber.log.Timber

/**
 * Created by farhad-mbp on 11/4/17.
 */

open class ZeroApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        mContext = applicationContext

        Timber.plant(Timber.DebugTree())
    }

    companion object {
        private var mContext: Context? = null

        fun getAppContext(): Context {
            return mContext!!
        }

    }
}
