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
    var products: ArrayList<ImageData>,
    var onTapClicked: (String?) -> Unit = {}
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
            imgPic.loadUrl(products[position].url, R.drawable.ic_launcher_foreground)
            imgPic.click { onTapClicked(products[position].url) }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return products.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setUpdatedData(products: ArrayList<ImageData>) {
        this.products = products
        notifyDataSetChanged()
    }

    fun clearData() {
        this.products.clear()
    }
}