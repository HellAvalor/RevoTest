package com.andrewkaraman.rtest.net

import android.os.Handler
import android.os.Looper
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.andrewkaraman.rtest.model.RevoCurrency
import com.andrewkaraman.rtest.model.RevoModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

var currencyList: MutableList<RevoCurrency> = ArrayList()
lateinit var mainHandler: Handler
var adapterList: MutableList<RecyclerView.Adapter<RecyclerView.ViewHolder>> = ArrayList()
var currencyName: String = "EUR"
var multiplier: Double = 1.0

private val updateDataTask = object : Runnable {

    override fun run() {
        startUpdateData()
        mainHandler.postDelayed(this, 1000)
    }
}

fun initHandler() {
    mainHandler = Handler(Looper.getMainLooper())
    startUpdate()
}

fun startUpdate() {
    mainHandler.post(updateDataTask)
}


fun stopUpdate() {
    mainHandler.removeCallbacks(updateDataTask)
}

fun addAdapter(adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) {
    adapterList.add(adapter)
}

fun removeAdapter(adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) {
    adapterList.remove(adapter)
}

fun startUpdateData() {
    val mApiService: APIService = RestClient.client.create(APIService::class.java)
    val call = mApiService.fetchQuestions(currencyName)
    call.enqueue(object : Callback<RevoModel> {

        override fun onResponse(call: Call<RevoModel>, response: Response<RevoModel>) {
            Timber.d("test")
            val questions = response.body()
            if (questions != null) {
                val newCurrencyList: MutableList<RevoCurrency> = ArrayList()
                newCurrencyList.add(RevoCurrency(questions.base, 1.0))
                questions.rates.forEach { (K, V) ->
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