package com.pd.nasapicturesapp.ui.grid

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pd.nasapicturesapp.R
import com.pd.nasapicturesapp.databinding.AdapterImagesListBinding
import com.pd.nasapicturesapp.models.ImageData
import com.pd.nasapicturesapp.utils.click
import com.pd.nasapicturesapp.utils.loadUrl

class ImagesListAdapter(
    var images: ArrayList<ImageData>,
    var onTapClicked: (Int?) -> Unit = {}
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var binding: AdapterImagesListBinding
    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = AdapterImagesListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        context = parent.context
        return MyHolder(binding)
    }

    class MyHolder(binding: AdapterImagesListBinding) : RecyclerView.ViewHolder(binding.root)

    @SuppressLint("StringFormatInvalid")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        with(binding) {
            imgPic.loadUrl(images[position].url, R.drawable.ic_launcher_foreground)
            imgPic.click { onTapClicked(position) }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return images.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setUpdatedData(images: ArrayList<ImageData>) {
        this.images = images
        notifyDataSetChanged()
    }

    fun clearData() {
        this.images.clear()
    }
}