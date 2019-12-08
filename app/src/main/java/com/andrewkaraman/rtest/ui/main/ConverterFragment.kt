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

class ConverterFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private var mAdapter: ConverterAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_main, container, false)

        recyclerView = root.findViewById(R.id.currencyRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        mAdapter = ConverterAdapter(
            currencyList,
            R.layout.editable_currency_item
        ) { position: Int -> partItemClicked(position) }
        recyclerView.adapter = mAdapter
        mAdapter!!.notifyDataSetChanged()
        addAdapter(mAdapter!!)
        return root
    }

    private fun partItemClicked(position: Int) {
        mAdapter!!.swapItem(position, 0)
        recyclerView.layoutManager!!.scrollToPosition(0)
    }

    companion object {

        @JvmStatic
        fun newInstance(): ConverterFragment {
            return ConverterFragment()
        }
    }
}