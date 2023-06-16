package com.bangkit.capstonebangkit.ui.adpater

import android.app.Activity
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.capstonebangkit.databinding.ContentColorBinding
import com.bangkit.capstonebangkit.models.ColorPicker

class ColorPickerAdapter : RecyclerView.Adapter<ColorPickerAdapter.ColorViewHolder>() {
    private val colorsData = ArrayList<ColorPicker>()

    fun setData(colors: ArrayList<ColorPicker>) {
        colorsData.clear()
        colorsData.addAll(colors)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ColorViewHolder(
        ContentColorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) =
        holder.bind(colorsData[position])

    override fun getItemCount() = colorsData.size

    inner class ColorViewHolder(private val binding: ContentColorBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ColorPicker) {
            with(binding) {
                cvColor.setCardBackgroundColor(ColorStateList.valueOf(Color.parseColor(item.valueColors)))
            }
        }
    }
}