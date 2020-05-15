package com.example.fastfooddiet.adapters

import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.fastfooddiet.viewcomponent.BarGraph

@BindingAdapter("topDrawable")
fun setTopDrawable(textView: TextView, resource : String) {

    val resourceId = textView.context.run {
        resources.getIdentifier(resource, "drawable", packageName)
    }

    textView.setCompoundDrawablesRelativeWithIntrinsicBounds(0,resourceId,0,0)
}

@BindingAdapter("imageResource")
fun setImageResource(imageView: ImageView, resource : String) {

    Log.d("xfast" ,"Set Image Resource: " + resource)

    val resourceId = imageView.context.run {
        resources.getIdentifier(resource, "drawable", packageName)
    }

    imageView.setImageResource(resourceId)
}

@BindingAdapter("barValue", "barLimit")
fun setBarGraph(barGraph: BarGraph, value: Int, limit: Int) {
    barGraph.setValues(value, limit)
}