package com.selflearning.chemistree.views

import android.content.Context
import android.graphics.Outline
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import android.view.ViewOutlineProvider
import android.widget.ImageView

class MyImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : androidx.appcompat.widget.AppCompatImageView(context, attrs, defStyleAttr) {


    init {
        outlineProvider = object : ViewOutlineProvider() {

            override fun getOutline(view: View, outline: Outline) {
                val path = Path()
                path.addCircle(
                    view.measuredWidth / 2f,
                    view.measuredHeight / 2f,
                    view.measuredWidth / 2f,
                    Path.Direction.CW
                )
                path.fillType = Path.FillType.INVERSE_EVEN_ODD

                outline.setConvexPath(path)
            }

        }
        clipToOutline = true
    }
}