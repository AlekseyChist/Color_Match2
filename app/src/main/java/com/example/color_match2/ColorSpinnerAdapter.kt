package com.example.color_match2

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class ColorSpinnerAdapter(context: Context, colors: List<Color>) : ArrayAdapter<Color>(context, android.R.layout.simple_spinner_item, colors) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getView(position, convertView, parent)
        val color = getItem(position)
        view.findViewById<TextView>(android.R.id.text1).apply {
            text = color?.name
            setTextColor(android.graphics.Color.parseColor(color?.hexCode))
        }
        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getView(position, convertView, parent)
    }
}