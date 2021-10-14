package com.bookit.koriramos.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bookit.domain.Car
import com.bookit.koriramos.R
import com.bookit.koriramos.activities.AvailableActivity
import com.bookit.koriramos.adapter.CarsRecyclerviewAdapter
import com.bookit.koriramos.viewmodels.MainViewModel
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
	private val mainViewModel: MainViewModel by viewModel()
	private lateinit var carsRecyclerView: RecyclerView
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
	}
	
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_home, container, false)
	}
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		carsRecyclerView = view.findViewById(R.id.topDealsRecyclerView)
		carsRecyclerView.layoutManager =
			LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
		
		with(view) {
			findViewById<ConstraintLayout>(R.id.available).setOnClickListener {
				startActivity(Intent(requireContext(), AvailableActivity::class.java))
			}
			val profileImage = findViewById<ImageView>(R.id.imageView4)
//			try {
//				Glide.with(this)
//					.load(
//						"https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.freepik.com%2Ffree-photos-vectors%2Fpersonal-profile&psig=AOvVaw2S-kE428TGIcwwtEL_IHl1&ust=1634283178328000&source=images&cd=vfe&ved=0CAsQjRxqFwoTCMCEw7SxyfMCFQAAAAAdAAAAABAJ"
//					).error(R.drawable.car)
//					.centerCrop()
//					.into(profileImage)
//			} catch (exception: Exception) {
//				Toast.makeText(requireContext(), exception.message.toString(), Toast.LENGTH_SHORT).show()
//			}
		}
		
		with(mainViewModel) {
			getAvailableCars()
			
			cars.observe(viewLifecycleOwner, {
				showTopDealsCars(it)
			})
		}
	}
	
	private fun showTopDealsCars(cars: List<Car>) {
		carsRecyclerView.adapter = CarsRecyclerviewAdapter(requireContext(), cars)
	}
}