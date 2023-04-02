package com.example.coordinatorlayoutdemo.rv.rv

import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RVAdapter: RecyclerView.Adapter<CommonViewHolder>() {
    override fun getItemCount(): Int {
        return 30
    }

    override fun onBindViewHolder(holder: CommonViewHolder, position: Int) {
        //do nothing
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonViewHolder {
        return CommonViewHolder(CommonTextView(parent.context))
    }

}


class CommonViewHolder(val view: View): RecyclerView.ViewHolder(view)

class CommonTextView(context: Context): FrameLayout(context) {
    private lateinit var textView: TextView

    init {
        textView = TextView(context).apply {
            text = "测试"
            gravity = Gravity.CENTER
        }
        addView(textView, FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, 100))
    }
}