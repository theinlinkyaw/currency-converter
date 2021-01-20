package com.tlk.currencyconverter.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.tlk.currencyconverter.data.model.Currency

class CurrencyAdapter (context: Context, val resource: Int, val objects: MutableList<Currency>)
    : ArrayAdapter<Currency>(context, resource, objects) {

    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: layoutInflater.inflate(resource, parent, false)
        (view as TextView).text = getItem(position).toString()
        return super.getView(position, convertView, parent)
    }

    override fun getCount(): Int {
        return objects.count()
    }

    override fun getItem(position: Int): Currency? {
        return objects[position]
    }
}