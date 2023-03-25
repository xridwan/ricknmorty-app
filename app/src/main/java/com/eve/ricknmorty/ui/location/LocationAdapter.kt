package com.eve.ricknmorty.ui.location

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.eve.domain.model.Location
import com.eve.ricknmorty.R
import com.eve.ricknmorty.databinding.ItemEpisodeBinding

class LocationAdapter(
    private val listener: Listener,
) : RecyclerView.Adapter<LocationAdapter.EpisodeViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<Location>() {
        override fun areItemsTheSame(oldItem: Location, newItem: Location): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Location, newItem: Location): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffCallback)

//    inner class EpisodeViewHolder(private val binding: ItemEpisodeBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//        fun bindData(data: Episode) {
//            binding.apply {
//                txtEpisode.text = data.episode
//                txtName.text = data.name
//                txtDate.text = data.air_date
//            }
//        }
//    }

    inner class EpisodeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemEpisodeBinding.bind(view)
        fun bindData(data: Location) {
            binding.apply {
                txtEpisode.text = data.created
                Log.d("EpisodeFragment", "bindData: ${data.name}")
                txtName.text = data.name
                txtDate.text = data.type
            }
            itemView.setOnClickListener {
                listener.listener(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
//        return EpisodeViewHolder(ItemEpisodeBinding.inflate(LayoutInflater.from(parent.context),
//            parent, false))

        return EpisodeViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_location, parent, false))

    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        holder.bindData(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    interface Listener {
        fun listener(data: Location)
    }
}