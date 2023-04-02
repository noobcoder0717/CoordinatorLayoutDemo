package com.example.coordinatorlayoutdemo.main

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.example.coordinatorlayoutdemo.MoveView
import java.util.*

class ColorBehavior(val context: Context, attrs: AttributeSet?) :
    CoordinatorLayout.Behavior<AppCompatImageView>(context, attrs) {

    companion object {
        const val TAG = "ColorBehavior"
    }

    /*
     * TODO 判断跟随变化的 View
     * @param parent: CoordinatorLayout
     * @param child: 当前的 view
     * @param dependency: 需要依赖的 View
     */
    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: AppCompatImageView,
        dependency: View
    ): Boolean {
        return dependency is MoveView
    }

    // 改变当前的状态
    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: AppCompatImageView,
        dependency: View
    ): Boolean {
        // 随机颜色
        child.setBackgroundColor(context.randomColor())
        return super.onDependentViewChanged(parent, child, dependency)
    }

    override fun onLayoutChild(
        parent: CoordinatorLayout,
        child: AppCompatImageView,
        layoutDirection: Int
    ): Boolean {
        return super.onLayoutChild(parent, child, layoutDirection)
    }
}

fun Context.randomColor(): Int {
    val r = Random().nextInt(50) + 10
    val g = Random().nextInt(50) + 10
    val b = Random().nextInt(50) + 10
    val rgb = java.lang.StringBuilder().apply {
        append("#")
        append(r.toString())
        append(g.toString())
        append(b.toString())
    }
    Log.d("RGB", "${rgb.toString()}")
    return Color.parseColor(rgb.toString())
}
