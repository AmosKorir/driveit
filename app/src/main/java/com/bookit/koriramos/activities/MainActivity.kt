package com.bookit.koriramos.activities

import android.os.Bundle
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bookit.koriramos.R
import com.bookit.koriramos.R.layout
import com.bookit.koriramos.custom_view.CustomRadioGroup

class MainActivity : AppCompatActivity() {
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(layout.activity_main)

		val radioGroup = findViewById<RadioGroup>(R.id.bottomRadioGroup)
		
		radioGroup.setOnCheckedChangeListener { p0, p1 ->
			when (p1) {
				R.id.home -> {
				}
				else -> {
//					Toast.makeText(this, "Feature coming soon", Toast.LENGTH_SHORT).show()
				}
			}
		}
	}
	
}