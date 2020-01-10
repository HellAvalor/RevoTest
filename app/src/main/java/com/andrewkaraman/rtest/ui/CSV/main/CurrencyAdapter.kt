package com.andrewkaraman.rtest.ui.CSV.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andrewkaraman.rtest.model.RevoCurrency
import kotlinx.android.synthetic.main.currency_item.view.*

open class CurrencyAdapter(
    private val currencyList: List<RevoCurrency>,
    private val mRowLayout: Int
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CurrencyViewHolder).bind(currencyList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(mRowLayout, parent, false)
        return CurrencyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return currencyList.size
    }

    class CurrencyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(currency: RevoCurrency) {
            itemView.currencyNameView.text = currency.currency
            itemView.currencyRateView.text = "%.2f".format(currency.rate)
        }
    }
}
