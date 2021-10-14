package com.bookit.koriramos.custom_view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet

class RoundedImage(context: Context,attrs: AttributeSet?): androidx.appcompat.widget.AppCompatImageView(context,attrs) {
	private val radius = 100.0f
	private var path: Path? = null
	private var rect: RectF? = null
	
	
	init {
		path = Path()
	}
	
	
	protected override fun onDraw(canvas: Canvas) {
		rect = RectF(0f, 0f, this.width.toFloat(), this.getHeight().toFloat())
		path?.addRoundRect(rect!!, radius, radius, Path.Direction.CW)
		canvas.clipPath(path!!)
		super.onDraw(canvas)
	}
}