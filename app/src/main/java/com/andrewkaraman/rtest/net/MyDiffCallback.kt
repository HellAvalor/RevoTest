package com.andrewkaraman.rtest.net

import androidx.recyclerview.widget.DiffUtil
import com.andrewkaraman.rtest.model.RevoCurrency


internal class MyDiffCallback(
    private var newPersons: List<RevoCurrency>,
    private var oldPersons: List<RevoCurrency>
) :
    DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldPersons.size
    }

    override fun getNewListSize(): Int {
        return newPersons.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldPersons[oldItemPosition].currency.equals(newPersons[newItemPosition].currency)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldPersons[oldItemPosition].rate == newPersons[newItemPosition].rate
    }
}