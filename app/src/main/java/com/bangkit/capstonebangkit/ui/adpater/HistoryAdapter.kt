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
import com.bangkit.capstonebangkit.databinding.ContentHistoryBinding
import com.bangkit.capstonebangkit.models.ColorPicker
import com.bangkit.capstonebangkit.models.HistoryResponse
import com.bangkit.capstonebangkit.utils.loadImage

class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {
    private val colorsData = ArrayList<HistoryResponse>()
    private var dataColor = arrayListOf<ColorPicker>()

    private lateinit var colorAdapter: ColorPickerAdapter

    fun setData(colors: ArrayList<HistoryResponse>) {
        colorsData.clear()
        colorsData.addAll(colors)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = HistoryViewHolder(
        ContentHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) =
        holder.bind(colorsData[position])

    override fun getItemCount() = colorsData.size

    inner class HistoryViewHolder(private val binding: ContentHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: HistoryResponse) {
            colorAdapter = ColorPickerAdapter()

            dataColor.clear()
            dataColor.add(ColorPicker(1, "#B68A65"))
            dataColor.add(ColorPicker(2, "#FFBB86FC"))
            dataColor.add(ColorPicker(3, "#C8BBCD"))
            dataColor.add(ColorPicker(4, "#FF03DAC5"))

            colorAdapter.setData(dataColor)

            with(binding) {
                tvName.text = item.name
                tvDate.text = item.date
                ivPreview.loadImage(item.image)
                with(rvColors) {
                    setHasFixedSize(true)
                    rvColors.adapter = colorAdapter
                }
            }
        }
    }
}