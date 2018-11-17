package ir.beigirad.zeroapplication.widget

import android.graphics.Color
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.snackbar.Snackbar
import ir.beigirad.app.R
import ir.beigirad.zeroapplication.ZeroApplication

/**
 * Created by farhad-mbp on 9/11/17.
 */

class RtlSnackbar {
    private var snackbar: Snackbar? = null
    private var snackView: View? = null

    class Builder {
        private var mRootView: View? = null
        private var mGravity: Int = Gravity.BOTTOM
        private var mText: String = ""
        private var mBtnText: String? = null
        private var mListener: SnackClickListener? = null
        private var mDuration: Int = Snackbar.LENGTH_LONG

        fun rootView(rootView: View): Builder {
            mRootView = rootView
            return this
        }

        fun text(text: String): Builder {
            mText = text
            return this
        }

        fun btnText(btnText: String?, btnListener: SnackClickListener?): Builder {
            mBtnText = btnText
            mListener = btnListener
            return this
        }

        fun gravity(gravit: Int): Builder {
            mGravity = gravit
            return this
        }

        fun duration(duration: Int): Builder {
            mDuration = duration
            return this
        }

        fun build(): RtlSnackbar {
            val snackbar = RtlSnackbar()
            snackbar.init(mRootView, mText, mDuration, mGravity)
            if (!mBtnText.isNullOrBlank())
                snackbar.addAction(mBtnText, mListener)
            return snackbar
        }

    }

    public fun show() {
        snackbar?.show()
    }

    private fun init(rootView: View?, text: String, duration: Int, gravity: Int) {
        if (rootView == null)
            return
        snackbar = Snackbar.make(rootView, text, duration)
        val layout = snackbar!!.view as Snackbar.SnackbarLayout

//        val textView = layout.findViewById<View>(android.support.design.R.id.snackbar_text) as TextView
//        textView.visibility = View.INVISIBLE

        snackView = LayoutInflater.from(rootView.context).inflate(R.layout.snackbar_custom, null)


        val textViewTop = snackView!!.findViewById<TextView>(R.id.snackbar_text)
        textViewTop.text = text
        textViewTop.setTextColor(Color.WHITE)

        try {
            val params = layout.layoutParams as CoordinatorLayout.LayoutParams
            params.gravity = gravity
            layout.layoutParams = params
        } catch (e: Throwable) {
//            e.printStackTrace()
        }

        layout.addView(snackView, 0)
    }

    private fun addAction(btnText: CharSequence?, btnListener: SnackClickListener?) {
        val snackbarButton = snackView!!.findViewById<Button>(R.id.snackbar_button)
        snackbarButton.text = btnText ?: ZeroApplication.getAppContext().getString(R.string.try_again)
        snackbarButton.visibility = View.VISIBLE
        snackbarButton.setOnClickListener {
            if (btnListener != null) {
                btnListener.onSnackClickListener()
                snackbar!!.dismiss()
            }
        }
    }

    interface SnackClickListener {
        fun onSnackClickListener()
    }
}
