package com.eve.ricknmorty.ui.character

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.eve.domain.model.Character
import com.eve.ricknmorty.R
import com.eve.ricknmorty.databinding.ItemCharacterBinding
import com.squareup.picasso.Picasso

class CharacterAdapter : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffCallback)

    class CharacterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemCharacterBinding.bind(view)

        fun bindData(data: Character) {
            binding.apply {
                Picasso.get().load(data.image).into(imgProfile)
                txtName.text = data.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_character, parent, false))
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bindData(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}