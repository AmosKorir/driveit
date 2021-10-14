package com.bookit.koriramos.custom_view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Bitmap.Config.ARGB_8888
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.graphics.drawable.BitmapDrawable
import android.text.TextPaint
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.widget.AppCompatRadioButton
import com.bookit.koriramos.R
import java.util.Locale

@SuppressLint("CustomViewStyleable")
class CustomRadioGroup(context: Context, val attributes: AttributeSet) :
	AppCompatRadioButton(context, attributes) {
	
	lateinit var view: View
	lateinit var paint: Paint
	lateinit var circlePaint: Paint
	val textPaint = textPainter()
	val areaRect = Rect(0, 0, 80, 80)
	val bitmap = Bitmap.createBitmap(80, 80, ARGB_8888)
	val bounds = RectF(areaRect)
	var count = 4
	
	init {
		val ta = context.obtainStyledAttributes(
			attributes,
			com.bookit.koriramos.R.styleable.CustomTextView,
			0,
			0
		)
		try {
			count = ta.getInt(com.bookit.koriramos.R.styleable.CustomTextView_customTypeface, 0)
		} catch (e: Exception) {
		
		}
		inflateLayout(context)
		paint = Paint()
		circlePaint = Paint()
	}
	
	fun notification(count: Int) {
		this.count = count
		invalidate()
	}
	
	override fun onDraw(canvas: Canvas?) {
		inflateLayout(context)
		var canvase = canvas
		if (this.isChecked) {
			
			this.textScaleX = 1F
		} else {
			if (count != 0) {
				if (id == R.id.account) {
					canvase = drawCanvas(canvas!!)
				}
			}
			this.textScaleX = 0F
		}
		
		
		
		
		
		
		super.onDraw(canvase)
		
	}
	
	private fun inflateLayout(context: Context) {
		view = LayoutInflater.from(context).inflate(R.layout.custom_radio_button, null)
		view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
		view.layout(0, 0, view.measuredWidth, view.measuredHeight)
		view.buildDrawingCache(false)
	}
	
	private fun drawCanvas(canvas: Canvas): Canvas {
		
		val size = 80
		val bmp=AvatarGenerator.avatarImage(context,50,1,"4").bitmap
		canvas.drawBitmap(bmp, 100f,70f, paint)
		return canvas
	}
	
	private fun textPainter(): TextPaint {
		val textPaint = TextPaint()
		textPaint.isAntiAlias = true
		textPaint.textSize = 16 * context.resources.displayMetrics.scaledDensity
		textPaint.color = Color.GREEN
		return textPaint
	}
}

class AvatarGenerator {
	companion object {
		@SuppressLint("StaticFieldLeak")
		private lateinit var uiContext: Context
		private var texSize = 0F
		
		fun avatarImage(context: Context, size: Int, shape: Int, name: String): BitmapDrawable {
			return avatarImageGenerate(context, size, shape, name, 700)
		}
		
		
		fun avatarImage(
			context: Context,
			size: Int,
			shape: Int,
			name: String,
			colorModel: Int
		): BitmapDrawable {
			return avatarImageGenerate(context, size, shape, name, colorModel)
		}
		
		private fun avatarImageGenerate(
			context: Context,
			size: Int,
			shape: Int,
			name: String,
			colorModel: Int
		): BitmapDrawable {
			uiContext = context
			
			texSize = calTextSize(size)
			val label = firstCharacter(name)
			val textPaint = textPainter()
			val painter = painter()
			painter.isAntiAlias = true
			val areaRect = Rect(0, 0, size, size)
			
			if (shape == 0) {
				val firstLetter = firstCharacter(name)
				val r = firstLetter[0]
				painter.color = Color.RED
			} else {
				painter.color = Color.TRANSPARENT
			}
			
			val bitmap = Bitmap.createBitmap(size, size, ARGB_8888)
			val canvas = Canvas(bitmap)
			canvas.drawRect(areaRect, painter)
			
			//reset painter
			if (shape == 0) {
				painter.color = Color.TRANSPARENT
			} else {
				val firstLetter = firstCharacter(name)
				val r = firstLetter[0]
				painter.color =Color.RED
			}
			
			val bounds = RectF(areaRect)
			bounds.right = textPaint.measureText(label, 0, 1)
			bounds.bottom = textPaint.descent() - textPaint.ascent()
			
			bounds.left += (areaRect.width() - bounds.right) / 2.0f
			bounds.top += (areaRect.height() - bounds.bottom) / 2.0f
			
			canvas.drawCircle(size.toFloat() / 2, size.toFloat() / 2, size.toFloat() / 2, painter)
			canvas.drawText(label, bounds.left, bounds.top - textPaint.ascent(), textPaint)
			return BitmapDrawable(uiContext.resources, bitmap)
			
		}
		
		
		private fun firstCharacter(name: String): String {
			return name.first().toString().toUpperCase(Locale.ROOT)
		}
		
		private fun textPainter(): TextPaint {
			val textPaint = TextPaint()
			textPaint.isAntiAlias = true
			textPaint.textSize = texSize * uiContext.resources.displayMetrics.scaledDensity
			textPaint.color = Color.WHITE
			return textPaint
		}
		
		private fun painter(): Paint {
			return Paint()
		}
		
		private fun calTextSize(size: Int): Float {
			return (12).toFloat()
		}
	}
}

