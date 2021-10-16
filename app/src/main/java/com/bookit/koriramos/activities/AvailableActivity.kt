package com.bookit.koriramos.activities

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bookit.domain.Car
import com.bookit.koriramos.R
import com.bookit.koriramos.adapter.GridCarsRecyclerviewAdapter
import com.bookit.koriramos.viewmodels.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AvailableActivity : AppCompatActivity() {
	private val mainViewModel: MainViewModel by viewModel()
	private lateinit var carsRecyclerView: RecyclerView
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_available)
		intent.extras?.getString("title")?.let {
			findViewById<TextView>(R.id.textView9).text=it
		}
		carsRecyclerView = findViewById(R.id.availableCarsRecyclerView)
		val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
		carsRecyclerView.layoutManager = staggeredGridLayoutManager
		with(mainViewModel) {
			getAvailableCars()
			
			cars.observe(this@AvailableActivity, {
				showTopDealsCars(it)
			})
		}
		
		findViewById<ImageView>(R.id.backArrow).setOnClickListener {
			onBackPressed()
		}
	}
	
	private fun showTopDealsCars(cars: List<Car>) {
		carsRecyclerView.adapter = GridCarsRecyclerviewAdapter(this, cars)
	}
}