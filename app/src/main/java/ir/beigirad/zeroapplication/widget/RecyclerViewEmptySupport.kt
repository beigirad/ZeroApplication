package ir.beigirad.zeroapplication.widget

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by farhad-mbp on 8/27/17.
 */

class RecyclerViewEmptySupport : RecyclerView {
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)


    //region handle empty place holder
    private var emptyView: View? = null

    private val observer = object : RecyclerView.AdapterDataObserver() {
        override fun onChanged() {
            showEmptyView()
        }

        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            super.onItemRangeInserted(positionStart, itemCount)
            showEmptyView()
        }

        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
            super.onItemRangeRemoved(positionStart, itemCount)
            showEmptyView()
        }
    }

    fun showEmptyView() {

        val adapter = adapter
        if (adapter != null && emptyView != null) {
            if (adapter.itemCount == 0) {
                emptyView!!.visibility = View.VISIBLE
                this@RecyclerViewEmptySupport.visibility = View.GONE
            } else {
                emptyView!!.visibility = View.GONE
                this@RecyclerViewEmptySupport.visibility = View.VISIBLE
            }
        }

    }

    override fun setAdapter(adapter: RecyclerView.Adapter<*>?) {
        super.setAdapter(adapter)
        if (adapter != null) {
            adapter.registerAdapterDataObserver(observer)
            observer.onChanged()
        }
    }

    fun setEmptyView(v: View) {
        emptyView = v
    }
    //endregion

    //region handle empty location click
    private var isValid = false
    private var x: Int = 0
    private var y: Int = 0
    // this will help us to understand whether the event can be considered a touch or scroll
    private val delta: Int = ViewConfiguration.get(context).scaledTouchSlop

    override fun onTouchEvent(e: MotionEvent?): Boolean {
        val onTouchEvent = super.onTouchEvent(e)
        when (e?.action) {
            MotionEvent.ACTION_DOWN -> {
                // saving initial touch location
                x = e.rawX.toInt()
                y = e.rawY.toInt()
                isValid = true
            }
            MotionEvent.ACTION_MOVE -> {
                if (Math.abs(e.rawX - x) > delta ||
                        Math.abs(e.rawY - y) > delta) {
                    // if a scroll happens - no longer consider this movement as valid
                    // this is needed for handling scroll on the inner part of `RecyclerView`
                    isValid = false
                }
            }
            MotionEvent.ACTION_UP -> {
                if (isValid && Math.abs(e.rawX - x) < delta &&
                        Math.abs(e.rawY - y) < delta) {
                    performClick()
                }
            }
        }
        return onTouchEvent
    }

    // This is needed in order to handle the edge case, when a click listener
    // would be fired when performing a click between the items of `RecyclerView`
    private fun isInRightArea(e: MotionEvent): Boolean {
        val r = Rect()
        getGlobalVisibleRect(r)
        r.left = paddingLeft
        r.top = r.top + paddingTop
        return !r.contains(e.rawX.toInt(), e.rawY.toInt())
    }
    //endregion


}
