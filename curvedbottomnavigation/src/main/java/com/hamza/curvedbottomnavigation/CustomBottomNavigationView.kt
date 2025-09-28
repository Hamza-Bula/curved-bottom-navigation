package com.hamza.curvedbottomnavigation


import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.view.animation.Interpolator
import android.view.animation.OvershootInterpolator
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlin.properties.Delegates
import androidx.core.graphics.toColorInt
import com.hamza.curvedbottomnavigation.R

class CustomBottomNavigationView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    interface OnItemSelectedListener {
        fun onItemSelected(itemId: Int)
    }

    var navBackgroundColor: Int = "#2E2E2E".toColorInt()
        set(value) {
            field = value
            backgroundPaint.color = value
            invalidate()
        }

    var selectedIconBackgroundColor: Int = "#2E2E2E".toColorInt()
        set(value) {
            field = value
            selectedIconBackgroundPaint.color = value
            invalidate()
        }

    var borderColor: Int = Color.WHITE
        set(value) {
            field = value
            borderPaint.color = value
            invalidate()
        }

    var selectedIconColor: Int = Color.WHITE
        set(value) {
            field = value
            invalidate()
        }

    var unselectedIconColor: Int = Color.LTGRAY
        set(value) {
            field = value
            invalidate()
        }

    var navHeight: Float = 200f
        set(value) {
            field = value
            requestLayout()
        }

    var bubbleRadius: Float = 200f
        set(value) {
            field = value
            invalidate()
        }

    var selectedIconSize: Int = 68
        set(value) {
            field = value
            invalidate()
        }

    var unselectedIconSize: Int = 64
        set(value) {
            field = value
            invalidate()
        }

    var selectedIconBackgroundRadius: Float = 46f
        set(value) {
            field = value
            invalidate()
        }

    var borderStrokeWidth: Float = 3f
        set(value) {
            field = value
            borderPaint.strokeWidth = value
            invalidate()
        }


    var selectedIconY: Float = 50f
        set(value) {
            field = value
            selectedIconTargetY = value
            if (!isInAnimation) {
                selectedIconCurrentY = value
            }
            invalidate()
        }

    var bubbleWidthMultiplier: Float = 2.0f
        set(value) {
            field = value
            invalidate()
        }

    var bubbleHeightFactor: Float = 0.6f
        set(value) {
            field = value
            invalidate()
        }

    var bubbleCurveFactor: Float = 0.2f
        set(value) {
            field = value
            invalidate()
        }

    var bubbleEdgeFactor: Float = 0.4f
        set(value) {
            field = value
            invalidate()
        }

    var bubbleAnimationDuration: Long = 500
    var iconAnimationDuration: Long = 1000
    var bubbleAnimationInterpolator: Interpolator = DecelerateInterpolator()
    var iconPositionInterpolator: Interpolator = OvershootInterpolator(1.2f)
    var iconScaleInterpolator: Interpolator = OvershootInterpolator(1.5f)
    var iconScaleMin: Float = 0.3f
    var iconScaleMax: Float = 1f
    var iconScaleOvershoot: Float = 1.5f
    var iconPositionOvershoot: Float = 1.2f

    private val backgroundPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = navBackgroundColor
    }

    private val selectedIconBackgroundPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = selectedIconBackgroundColor
        style = Paint.Style.FILL
    }

    private val borderPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = borderColor
        style = Paint.Style.STROKE
        strokeWidth = borderStrokeWidth
    }

    private var itemWidth = 0f

    private var selectedIconCurrentY: Float = 0f
    private var selectedIconScale = 1f
    private var selectedIconTargetY: Float = 0f
    private var isInAnimation = false

    private val bubbleDrawable = BubbleShapeDrawable()
    private var navItems = mutableListOf<NavItem>()
    private var selectedItemPosition by Delegates.observable(0) { _, old, new ->
        if (old != new && navItems.isNotEmpty() && new < navItems.size) {
            animateBubbleTo(itemWidth * new + itemWidth / 2)
            animateSelectedIcon()
            itemSelectedListener?.onItemSelected(navItems[new].id)
        }
    }

    private var currentBubbleX = 0f
    private var itemSelectedListener: OnItemSelectedListener? = null


    init {
        selectedIconCurrentY = selectedIconY
        selectedIconTargetY = selectedIconY

        validateItemCount(navItems)

        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.CustomBottomNavigationView, 0, 0)
            try {
                    navBackgroundColor = typedArray.getColor(R.styleable.CustomBottomNavigationView_navBackgroundColor, navBackgroundColor)
                selectedIconBackgroundColor = typedArray.getColor(R.styleable.CustomBottomNavigationView_selectedIconBackgroundColor, selectedIconBackgroundColor)
                borderColor = typedArray.getColor(R.styleable.CustomBottomNavigationView_borderColor, borderColor)
                selectedIconColor = typedArray.getColor(R.styleable.CustomBottomNavigationView_selectedIconColor, selectedIconColor)
                unselectedIconColor = typedArray.getColor(R.styleable.CustomBottomNavigationView_unselectedIconColor, unselectedIconColor)

                navHeight = typedArray.getDimension(R.styleable.CustomBottomNavigationView_navHeight, navHeight)
                bubbleRadius = typedArray.getDimension(R.styleable.CustomBottomNavigationView_bubbleRadius, bubbleRadius)
                selectedIconSize = typedArray.getDimensionPixelSize(R.styleable.CustomBottomNavigationView_selectedIconSize, selectedIconSize)
                unselectedIconSize = typedArray.getDimensionPixelSize(R.styleable.CustomBottomNavigationView_unselectedIconSize, unselectedIconSize)
                selectedIconBackgroundRadius = typedArray.getDimension(R.styleable.CustomBottomNavigationView_selectedIconBackgroundRadius, selectedIconBackgroundRadius)
                borderStrokeWidth = typedArray.getDimension(R.styleable.CustomBottomNavigationView_borderStrokeWidth, borderStrokeWidth)

                val newSelectedIconY = typedArray.getDimension(R.styleable.CustomBottomNavigationView_selectedIconY, selectedIconY)
                selectedIconY = newSelectedIconY
                selectedIconCurrentY = newSelectedIconY
                selectedIconTargetY = newSelectedIconY

                bubbleWidthMultiplier = typedArray.getFloat(R.styleable.CustomBottomNavigationView_bubbleWidthMultiplier, bubbleWidthMultiplier)
                bubbleHeightFactor = typedArray.getFloat(R.styleable.CustomBottomNavigationView_bubbleHeightFactor, bubbleHeightFactor)
                bubbleCurveFactor = typedArray.getFloat(R.styleable.CustomBottomNavigationView_bubbleCurveFactor, bubbleCurveFactor)
                bubbleEdgeFactor = typedArray.getFloat(R.styleable.CustomBottomNavigationView_bubbleEdgeFactor, bubbleEdgeFactor)

                bubbleAnimationDuration = typedArray.getInt(R.styleable.CustomBottomNavigationView_bubbleAnimationDuration, bubbleAnimationDuration.toInt()).toLong()
                iconAnimationDuration = typedArray.getInt(R.styleable.CustomBottomNavigationView_iconAnimationDuration, iconAnimationDuration.toInt()).toLong()

                iconScaleMin = typedArray.getFloat(R.styleable.CustomBottomNavigationView_iconScaleMin, iconScaleMin)
                iconScaleMax = typedArray.getFloat(R.styleable.CustomBottomNavigationView_iconScaleMax, iconScaleMax)
                iconScaleOvershoot = typedArray.getFloat(R.styleable.CustomBottomNavigationView_iconScaleOvershoot, iconScaleOvershoot)
                iconPositionOvershoot = typedArray.getFloat(R.styleable.CustomBottomNavigationView_iconPositionOvershoot, iconPositionOvershoot)
            } catch (_: Exception) {
            } finally {
                typedArray.recycle()
            }
        }
        updatePaintObjects()

        updateInterpolators()
    }

    private fun updatePaintObjects() {
        backgroundPaint.color = navBackgroundColor
        selectedIconBackgroundPaint.color = selectedIconBackgroundColor
        borderPaint.color = borderColor
        borderPaint.strokeWidth = borderStrokeWidth
    }

    private fun updateInterpolators() {
        iconPositionInterpolator = OvershootInterpolator(iconPositionOvershoot)
        iconScaleInterpolator = OvershootInterpolator(iconScaleOvershoot)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        itemWidth = if (navItems.isNotEmpty()) width / navItems.size.toFloat() else 0f
        currentBubbleX = if (navItems.isNotEmpty()) itemWidth * selectedItemPosition + itemWidth / 2 else 0f
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val height = resolveSize((navHeight + paddingTop + paddingBottom).toInt(), heightMeasureSpec)
        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY))
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (navItems.isEmpty()) {
            canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), backgroundPaint)
            canvas.drawLine(0f, borderPaint.strokeWidth / 2f, width.toFloat(), borderPaint.strokeWidth / 2f, borderPaint)
            return
        }

        val bubbleCenterX = currentBubbleX
        val bubbleCenterY = bubbleRadius

        val bubblePath = bubbleDrawable.getClippingPath(
            bubbleCenterX, bubbleCenterY, bubbleRadius,
            bubbleWidthMultiplier, bubbleHeightFactor, bubbleCurveFactor, bubbleEdgeFactor
        )
        canvas.save()
        canvas.clipPath(bubblePath, Region.Op.DIFFERENCE)
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), backgroundPaint)
        canvas.restore()

        val left = (bubbleCenterX - bubbleRadius).toInt()
        val top = (bubbleCenterY - bubbleRadius).toInt()
        val right = (bubbleCenterX + bubbleRadius).toInt()
        val bottom = (bubbleCenterY + bubbleRadius).toInt()
        bubbleDrawable.setBounds(left, top, right, bottom)
        bubbleDrawable.draw(canvas)

        drawTopBorder(canvas, bubbleCenterX, bubbleCenterY, bubbleRadius)

        drawNavigationItems(canvas)
    }

    private fun drawTopBorder(canvas: Canvas, bubbleCenterX: Float, bubbleCenterY: Float, bubbleRadius: Float) {
        val strokeOffset = borderPaint.strokeWidth / 2f
        val borderY = strokeOffset

        val bubbleLeftX = bubbleCenterX - bubbleRadius * bubbleEdgeFactor * bubbleWidthMultiplier
        val bubbleRightX = bubbleCenterX + bubbleRadius * bubbleEdgeFactor * bubbleWidthMultiplier

        val borderPath = Path().apply {
            moveTo(0f, borderY)
            lineTo(bubbleLeftX, borderY)

            cubicTo(
                bubbleCenterX - bubbleRadius * bubbleCurveFactor * bubbleWidthMultiplier, borderY,
                bubbleCenterX - bubbleRadius * bubbleCurveFactor * bubbleWidthMultiplier, borderY + bubbleRadius * bubbleHeightFactor,
                bubbleCenterX, borderY + bubbleRadius * bubbleHeightFactor
            )
            cubicTo(
                bubbleCenterX + bubbleRadius * bubbleCurveFactor * bubbleWidthMultiplier, borderY + bubbleRadius * bubbleHeightFactor,
                bubbleCenterX + bubbleRadius * bubbleCurveFactor * bubbleWidthMultiplier, borderY,
                bubbleRightX, borderY
            )

            lineTo(width.toFloat(), borderY)
        }

        canvas.drawPath(borderPath, borderPaint)
    }

    private fun drawNavigationItems(canvas: Canvas) {
        navItems.forEachIndexed { i, item ->
            val x = itemWidth * i + itemWidth / 2

            val drawable = try {
                ContextCompat.getDrawable(context, item.iconResId)
            } catch (e: Exception) {
                null
            } ?: return@forEachIndexed

            if (i == selectedItemPosition) {
                canvas.drawCircle(
                    itemWidth * selectedItemPosition + itemWidth / 2,
                    selectedIconCurrentY,
                    selectedIconBackgroundRadius * selectedIconScale,
                    selectedIconBackgroundPaint
                )

                drawable.setTint(selectedIconColor)
                val animatedIconSize = (selectedIconSize * selectedIconScale).toInt()
                val selectedIconX = itemWidth * selectedItemPosition + itemWidth / 2
                val animatedIconLeft = (selectedIconX - animatedIconSize / 2).toInt()
                val animatedIconTop = (selectedIconCurrentY - animatedIconSize / 2).toInt()
                drawable.setBounds(
                    animatedIconLeft,
                    animatedIconTop,
                    animatedIconLeft + animatedIconSize,
                    animatedIconTop + animatedIconSize
                )
                drawable.draw(canvas)
            } else {
                drawable.setTint(unselectedIconColor)
                val iconLeft = (x - unselectedIconSize / 2).toInt()
                val iconTop = (height / 2 - unselectedIconSize / 2).toInt()
                drawable.setBounds(
                    iconLeft,
                    iconTop,
                    iconLeft + unselectedIconSize,
                    iconTop + unselectedIconSize
                )
                drawable.draw(canvas)
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return when (event?.action) {
            MotionEvent.ACTION_DOWN -> true
            MotionEvent.ACTION_UP -> {
                if (itemWidth > 0 && navItems.isNotEmpty()) {
                    selectedItemPosition = (event.x / itemWidth).toInt().coerceIn(0, navItems.size - 1)
                }
                true
            }
            else -> super.onTouchEvent(event)
        }
    }

    private fun animateBubbleTo(targetX: Float) {
        ValueAnimator.ofFloat(currentBubbleX, targetX).apply {
            duration = bubbleAnimationDuration
            interpolator = bubbleAnimationInterpolator

            addUpdateListener {
                currentBubbleX = it.animatedValue as Float
                invalidate()
            }
            start()
        }
    }

    private fun animateSelectedIcon() {
        isInAnimation = true
        selectedIconScale = iconScaleMin

        ValueAnimator.ofFloat(selectedIconCurrentY, selectedIconTargetY).apply {
            duration = iconAnimationDuration
            interpolator = iconPositionInterpolator
            addUpdateListener {
                selectedIconCurrentY = it.animatedValue as Float
                invalidate()
            }
            addListener(object : android.animation.AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: android.animation.Animator) {
                    isInAnimation = false
                }
            })
            start()
        }

        ValueAnimator.ofFloat(iconScaleMin, iconScaleMax).apply {
            duration = iconAnimationDuration
            interpolator = iconScaleInterpolator
            addUpdateListener {
                selectedIconScale = it.animatedValue as Float
                invalidate()
            }
            start()
        }
    }


    private fun validateItemCount(items: List<NavItem>): List<NavItem> {
        return when {
            items.isEmpty() -> {
                emptyList()
            }
            items.size == 1 -> {
                Toast.makeText(context, "Warning: Navigation needs at least 2 items for proper functionality.", Toast.LENGTH_LONG).show()
                items
            }
            items.size > 5 -> {
                Toast.makeText(context, "Warning: Navigation supports maximum 5 items. Using first 5 items only.", Toast.LENGTH_LONG).show()
                items.take(5)
            }
            else -> {
                items
            }
        }
    }

    fun setOnItemSelectedListener(listener: OnItemSelectedListener) {
        itemSelectedListener = listener
    }

    fun setSelectedItem(itemId: Int) {
        navItems.indexOfFirst { it.id == itemId }.takeIf { it != -1 }?.let {
            selectedItemPosition = it
        }
    }

    fun setNavigationItems(items: List<NavItem>) {
        navItems = validateItemCount(items).toMutableList()
        selectedItemPosition = 0
        if (width > 0) {
            itemWidth = if (navItems.isNotEmpty()) width / navItems.size.toFloat() else 0f
            currentBubbleX = if (navItems.isNotEmpty()) itemWidth * selectedItemPosition + itemWidth / 2 else 0f
        }
        requestLayout()
        invalidate()
    }

}