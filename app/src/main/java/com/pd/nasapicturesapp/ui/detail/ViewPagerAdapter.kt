package com.pd.nasapicturesapp.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pd.nasapicturesapp.R
import com.pd.nasapicturesapp.models.ImageData
import com.pd.nasapicturesapp.utils.loadUrl

class ViewPagerAdapter(private val data: ArrayList<ImageData>) : RecyclerView.Adapter<ViewPagerAdapter.PagerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        return PagerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapter_view_pager, parent, false))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.bind(data[position])
    }

    inner class PagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        private val tvDate: TextView = itemView.findViewById(R.id.tvDate)
        private val tvDescription: TextView = itemView.findViewById(R.id.tvDescription)
        private val imgPic: ImageView = itemView.findViewById(R.id.imgPic)

        fun bind(data: ImageData) {
            tvTitle.text = data.title
            tvDescription.text = data.explanation
            tvDate.text = data.date
            imgPic.loadUrl(data.hdurl, R.drawable.ic_launcher_foreground)
        }
    }
}