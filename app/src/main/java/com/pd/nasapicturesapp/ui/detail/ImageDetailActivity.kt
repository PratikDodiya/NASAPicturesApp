package com.pd.nasapicturesapp.ui.detail

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.pd.nasapicturesapp.R
import com.pd.nasapicturesapp.databinding.ActivityImageDetailBinding
import com.pd.nasapicturesapp.models.ImageData

class ImageDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityImageDetailBinding
    var position = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeView()
    }

    private fun initializeView() {
        setToolBar()

        binding.apply {

            position = intent.getIntExtra("Position", 0)
            val imagesData = intent.getParcelableArrayListExtra<ImageData>("Images") as ArrayList<ImageData>

            if (imagesData.isNullOrEmpty().not()) {
                viewPager.adapter = ViewPagerAdapter(imagesData)
                viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
                viewPager.currentItem = position
                viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        super.onPageSelected(position)
                    }
                })
            }
        }
    }

    private fun setToolBar() {
        // Update title text in Toolbar
        supportActionBar?.title = getString(R.string.details)

        // Show Back Arrow to Toolbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish() // close this activity and return to previous activity
        }

        return super.onOptionsItemSelected(item)
    }
}