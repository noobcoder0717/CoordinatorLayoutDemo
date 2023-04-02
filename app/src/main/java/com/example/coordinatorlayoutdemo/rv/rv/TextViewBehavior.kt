package com.example.coordinatorlayoutdemo.rv.rv

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import kotlin.math.abs

class TextViewBehavior (
    val context: Context,
    val attributeSet: AttributeSet
): CoordinatorLayout.Behavior<TextView>(context, attributeSet) {
    private val TAG = "TextViewBehavior"

    //相对y轴的滑动距离
    private var scrollY = 0

    //总共滑动的距离
    private var totalScrollY = 0

    override fun onLayoutChild(
        parent: CoordinatorLayout,
        child: TextView,
        layoutDirection: Int
    ): Boolean {
        parent.onLayoutChild(child, layoutDirection)
        return true
    }


    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: TextView,
        directTargetChild: View,
        target: View,
        axes: Int,
        type: Int
    ): Boolean {
        return true; //为了能正常收到CoordinatorLayout分发的事件
    }

    override fun onNestedPreScroll(
        coordinatorLayout: CoordinatorLayout,
        child: TextView,
        target: View,
        dx: Int,
        dy: Int,
        consumed: IntArray,
        type: Int
    ) {
        Log.d(TAG, "dy: $dy, consumed: $consumed")
        var consumedY = dy
        var calculateTotalScroll = totalScrollY + dy
        if (abs(calculateTotalScroll) > getMaxScroll(child)) {
            consumedY = getMaxScroll(child) - abs(totalScrollY) //最大滑动距离，减去当前总滑动距离，等于剩下的还能滑动的距离
        } else if (calculateTotalScroll < 0) {
            consumedY = 0
        }

        ViewCompat.offsetTopAndBottom(child, -consumedY)

        //重新赋值
        totalScrollY += consumedY
        consumed[1] = consumedY
    }

    private fun getMaxScroll(child: TextView): Int {
        //返回最大的可滑动距离，这里取的是TextView的高度值
        //如果totalScrollY比这个值大，就需要把剩下的未消耗的滑动距离重新还给RecyclerView
        return child.height
    }
}