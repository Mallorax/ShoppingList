package com.example.shoppinglist.ui

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("shoppingListDate")
fun dateBindingAdapter(textView: TextView, calendar: Calendar){
    val format = SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.US)
    textView.text = format.format(calendar.time)
}