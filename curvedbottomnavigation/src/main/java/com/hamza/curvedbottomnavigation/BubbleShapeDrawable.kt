package com.hamza.curvedbottomnavigation


import android.graphics.*
import android.graphics.drawable.Drawable

class BubbleShapeDrawable : Drawable() {

    private var radius = 0f
    private var centerX = 0f
    private var centerY = 0f

    override fun onBoundsChange(bounds: Rect) {
        super.onBoundsChange(bounds)

        centerX = bounds.exactCenterX()
        centerY = bounds.exactCenterY()
        val minDimension = minOf(bounds.width(), bounds.height())
        radius = minDimension / 2f
    }

    override fun draw(canvas: Canvas) {
    }
    fun getClippingPath(
        centerX: Float,
        centerY: Float,
        radius: Float,
        widthMultiplier: Float = 2.0f,
        heightFactor: Float = 0.6f,
        curveFactor: Float = 0.2f,
        edgeFactor: Float = 0.4f
    ): Path {
        return Path().apply {
            val top = centerY - radius

            moveTo(centerX - radius * edgeFactor * widthMultiplier, top)

            cubicTo(
                centerX - radius * curveFactor * widthMultiplier, top,
                centerX - radius * curveFactor * widthMultiplier, top + radius * heightFactor,
                centerX, top + radius * heightFactor
            )

            cubicTo(
                centerX + radius * curveFactor * widthMultiplier, top + radius * heightFactor,
                centerX + radius * curveFactor * widthMultiplier, top,
                centerX + radius * edgeFactor * widthMultiplier, top
            )

            close()
        }
    }

    override fun setAlpha(alpha: Int) {}
    override fun setColorFilter(colorFilter: ColorFilter?) {}
    override fun getOpacity(): Int = PixelFormat.TRANSLUCENT
}