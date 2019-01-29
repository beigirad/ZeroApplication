package ir.beigirad.zeroapplication.widget

import android.content.Context
import android.graphics.Point
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import ir.beigirad.app.R


/**
 * Created by farhad-mbp on 1/25/18.
 */
class CenterToolbar : Toolbar {
    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {
        init()
    }

    private lateinit var _titleTextView: TextView
    private lateinit var _imgView: ImageView
    private var _screenWidth: Int = 0
    private var _centerTitle = true

    private fun init() {
        _screenWidth = getScreenSize().x
        _titleTextView = TextView(context, null, R.style.ToolbarTitleText)
        _imgView = ImageView(context)
        _imgView.adjustViewBounds = true

        addView(_titleTextView)
        addView(_imgView)

    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)

        if (_centerTitle) {
            val location = IntArray(2)
            _titleTextView.getLocationOnScreen(location)
            _titleTextView.translationX = _titleTextView.translationX +
                    (-location[0] + _screenWidth / 2 - _titleTextView.width / 2)

            val location2 = IntArray(2)
            _imgView.getLocationOnScreen(location2)
            _imgView.translationX = _imgView.translationX +
                    (-location2[0] + _screenWidth / 2 - _imgView.width / 2)
        }
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        _imgView.layoutParams = _imgView.layoutParams.apply {
            this.height = (measuredHeight * 0.6).toInt()
        }

    }

    override fun setLogo(drawable: Drawable?) {
        super.setLogo(null)
        _imgView.setImageDrawable(drawable)
        requestLayout()
    }

    override fun setLogo(resId: Int) {
        super.setLogo(null)
        _imgView.setImageResource(resId)
        requestLayout()
    }

    override fun setTitle(title: CharSequence?) {
        super.setTitle(null)
        _titleTextView.text = title
        requestLayout()

    }

    override fun setTitle(resId: Int) {
        super.setTitle(null)
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