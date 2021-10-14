package com.bookit.koriramos.custom_view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatRadioButton
import com.bookit.koriramos.R

class CustomRadioGroup(context: Context, attributes: AttributeSet) :
	AppCompatRadioButton(context, attributes) {
	lateinit var title: TextView
	lateinit var icon: ImageView
	lateinit var view: View
	
	init {
		inflateLayout(context)
	}
	
	override fun setOnCheckedChangeListener(listener: OnCheckedChangeListener?) {
		
		if (this.isChecked){
			Toast.makeText(context, "yo", Toast.LENGTH_SHORT).show()
			this.text=""
		}
		super.setOnCheckedChangeListener(listener)
	}
	override fun onDraw(canvas: Canvas?) {
		inflateLayout(context)
	
		if (this.isChecked){
			this.textScaleX=1F
		}else{
			this.textScaleX=0F
		}
		
		super.onDraw(canvas)
		
	}
	
	private fun inflateLayout(context: Context) {
		view = LayoutInflater.from(context).inflate(R.layout.custom_radio_button, null)
		view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
		view.layout(0, 0, view.measuredWidth, view.measuredHeight)
		view.buildDrawingCache(false)
	}
	
}

