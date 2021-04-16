package com.genxaswhiz.library.utility

import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import org.jetbrains.anko.forEachChildWithIndex

class CustomDividerItemDecoration(private val divider: Drawable,
                                  private val dividerViewType: Int) : RecyclerView.ItemDecoration() {

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        c.save()
        val dividerLeft = parent.paddingLeft
        val dividerRight = parent.width - parent.paddingRight

        parent.forEachChildWithIndex { index, view ->

            val viewType = parent.getChildViewHolder(view).itemViewType
            val nextPosition = index + 1
            if (nextPosition < parent.childCount) {
                val nextView = parent.getChildAt(nextPosition)
                val nextType = parent.getChildViewHolder(nextView).itemViewType

                if (viewType == dividerViewType && nextType == dividerViewType) {
                    val params = view.layoutParams as RecyclerView.LayoutParams
                    val dividerTop = view.bottom + params.bottomMargin
                    val dividerBottom = dividerTop + divider.intrinsicHeight

                    divider.setBounds(dividerLeft, dividerTop, dividerRight, dividerBottom)
                    divider.draw(c)
                }
            }
        }

        c.restore()
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        val viewType = parent.getChildViewHolder(view).itemViewType
        if (viewType == dividerViewType) {
            outRect.top = divider.intrinsicHeight
        }
    }
}