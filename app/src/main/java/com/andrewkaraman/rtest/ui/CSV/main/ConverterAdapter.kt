package com.andrewkaraman.rtest.ui.CSV.main

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andrewkaraman.rtest.model.RevoCurrency
import com.andrewkaraman.rtest.net.currencyName
import com.andrewkaraman.rtest.net.multiplier
import kotlinx.android.synthetic.main.currency_item.view.currencyNameView
import kotlinx.android.synthetic.main.editable_currency_item.view.*
import java.util.*

class ConverterAdapter(
    private val currencyList: List<RevoCurrency>,
    private val mRowLayout: Int,
    private var clickListener: (Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int {
        return currencyList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConverterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(mRowLayout, parent, false)
        return ConverterViewHolder(view)
    }

    fun swapItem(fromPosition: Int, toPosition: Int) {
        currencyName = currencyList[fromPosition].currency
        Collections.swap(currencyList, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ConverterViewHolder).bind(currencyList[position], position, clickListener)
    }

    class ConverterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val textUpdateWatcher = object :
            TextWatcher {
            override fun beforeTextChanged(
                charSequence: CharSequence,
                i: Int,
                i1: Int,
                i2: Int
            ) {

            }

            override fun onTextChanged(
                charSequence: CharSequence,
                i: Int,
                i1: Int,
                i2: Int
            ) {
                try {
                    multiplier = charSequence.toString().toDouble()
                    if (multiplier < 0) multiplier = 0.0
                } catch (e: NumberFormatException) {
                    multiplier = 0.0
                }
            }

            override fun afterTextChanged(editable: Editable) {

            }
        }

        fun bind(currency: RevoCurrency, position: Int, clickListener: (Int) -> Unit) {
            itemView.currencyNameView.text = currency.currency
            itemView.editCurrencyRateView.isEnabled = position == 0
            if (position == 0) {
                itemView.editCurrencyRateView.editCurrencyRateView.addTextChangedListener(
                    textUpdateWatcher
                )
                itemView.editCurrencyRateView.setText("%.2f".format(multiplier))
            } else {
                itemView.editCurrencyRateView.editCurrencyRateView.removeTextChangedListener(
                    textUpdateWatcher
                )
                itemView.editCurrencyRateView.setText("%.2f".format(currency.rate!! * (if (position == 0) 1.0 else multiplier)))
            }
            itemView.setOnClickListener { clickListener(position) }
        }
    }
}