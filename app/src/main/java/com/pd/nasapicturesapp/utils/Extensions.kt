package com.pd.nasapicturesapp.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.AssetManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

//Recyclerview Manager
fun RecyclerView.setManager(
    isItHorizontal: Boolean = false,
    isItGrid: Boolean = false,
    spanCount: Int = 2
) {
    if (isItGrid)
        this.layoutManager = GridLayoutManager(this.context, spanCount)
    else {
        if (isItHorizontal)
            this.layoutManager = LinearLayoutManager(this.context, RecyclerView.HORIZONTAL, false)
        else
            this.layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
    }


}

/**
 * Set an onclick listener
 */
fun View.click(block: () -> Unit) = setOnClickListener { block.invoke() }

//Toast
fun Context.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, message, duration).show()

fun Fragment.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this.context, message, duration).show()

// TAG - classname prints in main method only but can't prints in retrofit inner methods or others
val Any.TAG: String
    get() {
//        val tag = javaClass.simpleName
//        return if (tag.length <= 23) tag else tag.substring(0, 23)
        return if (!javaClass.isAnonymousClass) {
            val name = javaClass.simpleName
            if (name.length <= 23) name else name.substring(0, 23)// first 23 chars
        } else {
            val name = javaClass.name
            if (name.length <= 23) name else name.substring(name.length - 23, name.length)// last 23 chars
        }
    }

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

//Read file from Assets
fun AssetManager.readAssetsFile(fileName: String): String = open(fileName).bufferedReader().use { it.readText() }

//Load URL in ImageView
fun ImageView.loadUrl(url: String? = "", placeholder: Int) {
//    println("=======url======="+url)
    Glide.with(context).load(url).apply(RequestOptions().placeholder(placeholder)).into(this)
}

//Start Intent Activity
inline fun <reified T : Any> Activity.launchActivity(
    options: Bundle? = null,
    finishAffinity: Boolean = false,
    noinline init: Intent.() -> Unit = {}
) {

    val intent = newIntent<T>(this)
    intent.init()
    if (finishAffinity) {
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
        if (finishAffinity)
            finishAffinity()
        startActivity(intent, options)
    } else {
        if (finishAffinity)
            finishAffinity()
        startActivity(intent)
    }
}

inline fun <reified T : Any> newIntent(context: Context): Intent =
    Intent(context, T::class.java)
