package com.bookit.koriramos.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.marginTop
import androidx.recyclerview.widget.RecyclerView
import com.bookit.domain.Car
import com.bookit.koriramos.R
import com.bookit.koriramos.adapter.GridCarsRecyclerviewAdapter.CarsRecyclerviewAdapterViewHolder
import com.google.android.gms.dynamic.IFragmentWrapper

class GridCarsRecyclerviewAdapter(val context: Context, val cars: List<Car>) :
	RecyclerView.Adapter<CarsRecyclerviewAdapterViewHolder>() {
	
	class CarsRecyclerviewAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
	
	}
	
	override fun onCreateViewHolder(
		parent: ViewGroup,
		viewType: Int
	): CarsRecyclerviewAdapterViewHolder {
		return if (viewType==0){
			CarsRecyclerviewAdapterViewHolder(
				LayoutInflater.from(context).inflate(R.layout.cars_item_layout_large, parent, false)
			)
		} else{
			CarsRecyclerviewAdapterViewHolder(
				LayoutInflater.from(context).inflate(R.layout.cars_item_layout, parent, false)
			)
		}
		}
	
	override fun getItemViewType(position: Int): Int {
		
		return if(position==0){
		0
		}else{
			1
		}
	}
	
	override fun getItemCount(): Int {
		return cars.size
	}
	
	override fun onBindViewHolder(holder: CarsRecyclerviewAdapterViewHolder, position: Int) {
		val car = cars[position]
		with(holder.itemView) {
			
			with(car, {
				findViewById<TextView>(R.id.pricingPeriodTv).text = pricingPeriod
				findViewById<TextView>(R.id.carNameTv).text = name
				findViewById<TextView>(R.id.modelTv).text = price
				findViewById<TextView>(R.id.generalPeriodTv).text = unitPeriod
				
				findViewById<ImageView>(R.id.imageView7).setImageDrawable(
					context.getDrawable(
						imageUrl
					)
				)
				
			})
		}
	}
	
}