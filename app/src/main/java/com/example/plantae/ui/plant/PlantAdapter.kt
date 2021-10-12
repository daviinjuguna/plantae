package com.example.plantae.ui.plant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.example.plantae.R
import com.example.plantae.data.entity.Plants
import kotlinx.android.synthetic.main.item_plant.view.*

class PlantAdapter(private val listener: PlantItemListener) :
    RecyclerView.Adapter<PlantAdapter.PlantViewHolder>() {
    private var plantsList:List<Plants> = ArrayList()

    fun setItems(itemsNew: ArrayList<Plants>) {
//        items.clear()
        plantsList=itemsNew

        notifyDataSetChanged()
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantViewHolder {
        return PlantViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_plant, parent, false),
            listener
        )
    }

    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) =
        holder.bind(plantsList[position])

    override fun getItemCount(): Int = plantsList.size

    class PlantViewHolder(itemView: View, private val listener: PlantItemListener) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val plantName: TextView = itemView.name
        private val plantHabit: TextView = itemView.habit
        private val plantImage: AppCompatImageView = itemView.image

        private lateinit var plant: Plants
        override fun onClick(v: View?) {
            listener.onClickedPlant(plant)
        }

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(item: Plants) {
            this.plant = item

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(plant.image)
                .transform(CircleCrop())
                .into(plantImage)

            plantName.text = plant.name
            plantHabit.text = plant.habit
        }
    }
}