package com.example.androidsampleapp.crash

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class CrashTypeAdapter(context: Context, val items: List<CrashType>) :
    ArrayAdapter<CrashType>(context, 0) {

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return bind(position, convertView, parent, android.R.layout.simple_spinner_dropdown_item)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return bind(position, convertView, parent, android.R.layout.simple_spinner_item)
    }

    private fun bind(position: Int, convertView: View?, parent: ViewGroup, res: Int): View {
        val view = if (convertView == null) {
            val inflater = LayoutInflater.from(context)
            inflater.inflate(res, parent, false)
        } else {
            convertView
        }

        (view as TextView).text = context.getText(getItem(position).nameRes)

        return view
    }

    override fun getCount(): Int = items.size

    override fun getItem(position: Int): CrashType = items[position]
}
