package com.andrewkaraman.rtest.net

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.andrewkaraman.rtest.model.RevoCurrency
import com.andrewkaraman.rtest.model.RevoModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

var currencyList: MutableList<RevoCurrency> = ArrayList()
var adapterList: MutableList<RecyclerView.Adapter<RecyclerView.ViewHolder>> = ArrayList()
var currencyName: String = "EUR"
var multiplier: Double = 1.0

fun addAdapter(adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) {
    adapterList.add(adapter)
}

fun startUpdateData() {
    val mApiService: APIService = RestClient.client.create(APIService::class.java)
    val call = mApiService.fetchQuestions(currencyName)
    call.enqueue(object : Callback<RevoModel> {

        override fun onResponse(call: Call<RevoModel>, response: Response<RevoModel>) {
            Timber.d("test")
            val revoModel = response.body()
            revoModel?.let {
                val newCurrencyList: MutableList<RevoCurrency> = ArrayList()
                newCurrencyList.add(RevoCurrency(revoModel.base, 1.0))
                revoModel.rates.forEach { (K, V) ->
                    newCurrencyList.add(RevoCurrency(K, V))
                }
                val diffResult =
                    DiffUtil.calculateDiff(MyDiffCallback(currencyList, newCurrencyList))

                adapterList.forEach { adapter: RecyclerView.Adapter<RecyclerView.ViewHolder> ->
                    diffResult.dispatchUpdatesTo(
                        adapter
                    )
                }
                currencyList.clear()
                currencyList.addAll(newCurrencyList)
                newCurrencyList.clear()
            }
        }

        override fun onFailure(call: Call<RevoModel>, t: Throwable) {
            Timber.e("onFailure")
        }
    })
}

suspend fun loadLots() {
    coroutineScope {
        while (true) {
            delay(1_000)
            launch { startUpdateData() }
        }
    }
}