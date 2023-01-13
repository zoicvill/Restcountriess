package com.safr.restcountries.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.safr.restcountries.R
import com.safr.restcountries.data.model.CountryItem
import com.safr.restcountries.databinding.ItemLangBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


interface LandNumberClickListener {
    fun onItemClick(item: CountryItem)
}

class LandDiffUtil : DiffUtil.ItemCallback<CountryItem>() {

    override fun areItemsTheSame(oldItem: CountryItem, newItem: CountryItem) =
        oldItem.name == newItem.name

    override fun areContentsTheSame(oldItem: CountryItem, newItem:CountryItem) =
        oldItem == newItem

}


class LandAdapter(val onHisClickListener: LandNumberClickListener) :
    ListAdapter<CountryItem, LandAdapter.LandListViewHolder>(LandDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        LandListViewHolder(
            ItemLangBinding.bind(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_lang,
                    parent,
                    false
                )
            )
        )

    override fun onBindViewHolder(holder: LandListViewHolder, position: Int) =
        holder.bind(itemPosition = position)


    private fun loadImage(position: Int, viewBinding: ItemLangBinding) {
        CoroutineScope(Dispatchers.Main).launch {
                viewBinding.imageViewFlag
                    .loadImageUtils( currentList.get(position)?.flags?.png)

        }

    }

    inner class LandListViewHolder(private val binding: ItemLangBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(itemPosition: Int) {
            val land = getItem(itemPosition)

            binding.nameLandd.text = land.name
            loadImage(itemPosition, binding)

            binding.root.setOnClickListener {
                onHisClickListener.onItemClick(item =  land)
            }
        }
    }

}




