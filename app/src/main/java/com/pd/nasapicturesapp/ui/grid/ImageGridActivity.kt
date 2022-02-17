package com.pd.nasapicturesapp.ui.grid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.pd.nasapicturesapp.databinding.ActivityImageGridBinding
import com.pd.nasapicturesapp.helpers.GridSpacingItemDecoration
import com.pd.nasapicturesapp.models.ImageData
import com.pd.nasapicturesapp.utils.readAssetsFile
import com.pd.nasapicturesapp.utils.setManager
import kotlin.math.roundToInt

class ImageGridActivity : AppCompatActivity() {

    private lateinit var binding: ActivityImageGridBinding
    var adapter: ImagesListAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageGridBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeView()
    }

    private fun initializeView() {
        binding.rvImages.setManager(isItHorizontal = true, isItGrid = true)
        binding.rvImages.addItemDecoration(GridSpacingItemDecoration(2, resources.getDimension(com.intuit.sdp.R.dimen._10sdp).roundToInt(), true))

        val response = assets.readAssetsFile("data.json")
        val typeToken = object : TypeToken<ArrayList<ImageData>>() {}.type
        val imagesList = Gson().fromJson<ArrayList<ImageData>>(response, typeToken)
        updateAdapterData(imagesList)
    }

    private fun updateAdapterData(productsList: ArrayList<ImageData>) {
        adapter = ImagesListAdapter(productsList, onTapClicked = {
        })
        binding.rvImages.adapter = adapter
    }
}