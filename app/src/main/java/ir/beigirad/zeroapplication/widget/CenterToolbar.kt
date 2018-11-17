package ir.beigirad.zeroapplication.widget

import android.content.Context
import android.graphics.Point
import android.util.AttributeSet
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import ir.beigirad.app.R


/**
 * Created by farhad-mbp on 1/25/18.
 */
class CenterToolbar : Toolbar {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle)

    private var _titleTextView: TextView
    private var _screenWidth: Int = 0
    private var _centerTitle = true

    init {
        _screenWidth = getScreenSize().x
        _titleTextView = TextView(context)
        _titleTextView.setTextAppearance(context, R.style.ToolbarTitleText)
//        CalligraphyUtils.applyFontToTextView(context, _titleTextView, context.getString(R.string.font_bold))
        addView(_titleTextView)

    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)

        if (_centerTitle) {
            val location = IntArray(2)
            _titleTextView.getLocationOnScreen(location)
            _titleTextView.translationX = _titleTextView.translationX + (-location[0] + _screenWidth / 2 - _titleTextView.width / 2)
        }
    }

    override fun setTitle(title: CharSequence?) {
        super.setTitle("")
        _titleTextView.text = title
        requestLayout()
    }

    override fun setTitle(resId: Int) {
        super.setTitle("")
        _titleTextView.text = title
        requestLayout()
    }

    private fun getScreenSize(): Point {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = windowManager.defaultDisplay
        val screenSize = Point()
        display.getSize(screenSize)
        return screenSize
    }
}