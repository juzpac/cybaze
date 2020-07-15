package com.mvvmrxjavatemp.utils.ui

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView


class SpaceAdjust(val context: Context) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val currentPos = parent.getChildAdapterPosition(view)
        val itemCount = state.itemCount
        with(outRect) {
            top = 8.toPx
            when {
                currentPos % 3 == 0 -> left=8.toPx
                currentPos % 3 == 2 -> right=8.toPx
                else -> {
                    left=8.toPx
                    right=8.toPx
                }
            }
        }
    }
}