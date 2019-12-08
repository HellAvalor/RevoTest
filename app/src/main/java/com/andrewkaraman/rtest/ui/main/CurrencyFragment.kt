package com.andrewkaraman.rtest.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andrewkaraman.rtest.R
import com.andrewkaraman.rtest.net.addAdapter
import com.andrewkaraman.rtest.net.currencyList

class CurrencyFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private var mAdapter: CurrencyAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_main, container, false)

        recyclerView = root.findViewById(R.id.currencyRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        mAdapter = CurrencyAdapter(currencyList, R.layout.currency_item)
        recyclerView.adapter = mAdapter
        mAdapter!!.notifyDataSetChanged()
        addAdapter(mAdapter!!)
        return root
    }

    companion object {
        @JvmStatic
        fun newInstance(): CurrencyFragment {
            return CurrencyFragment()
        }
    }
}