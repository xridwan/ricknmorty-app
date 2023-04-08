package com.eve.ricknmorty.ui.episode

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.eve.domain.model.Episode
import com.eve.ricknmorty.R
import com.eve.ricknmorty.databinding.ItemEpisodeBinding

class EpisodeAdapter(
    private val listener: Listener,
) : RecyclerView.Adapter<EpisodeAdapter.EpisodeViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<Episode>() {
        override fun areItemsTheSame(oldItem: Episode, newItem: Episode): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Episode, newItem: Episode): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffCallback)

    inner class EpisodeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemEpisodeBinding.bind(view)
        fun bindData(data: Episode) {
            binding.apply {
                txtEpisode.text = data.episode
                txtName.text = data.name
                txtDate.text = data.air_date
            }
            itemView.setOnClickListener {
                listener.onClickListener(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        return EpisodeViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_episode, parent, false))
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        holder.bindData(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    interface Listener {
        fun onClickListener(data: Episode)
    }
}