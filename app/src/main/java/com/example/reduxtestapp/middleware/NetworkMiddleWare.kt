package com.example.reduxtestapp.middleware

import android.util.Log
import com.example.reduxtestapp.actions.DisplayRandomNumberFact
import com.example.reduxtestapp.actions.LoadRandomNumberFact
import com.example.reduxtestapp.network.model.NumberFactResponse
import com.example.reduxtestapp.network.provideNumberClient
import com.example.reduxtestapp.states.AppState
import org.rekotlin.DispatchFunction
import org.rekotlin.Middleware
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

internal val networkMiddleware: Middleware<AppState> = { dispatch, _ ->
    { next ->
        { action ->
            when (action) {
                is LoadRandomNumberFact -> {
                    callRandomNumberFact(dispatch)
                }
            }
            next(action)
        }
    }
}

private fun callRandomNumberFact(dispatch: DispatchFunction) {
    val numberFactsApi = provideNumberClient()
    val randomYearFact = numberFactsApi.getRandomYearFact()

    randomYearFact.enqueue(object : Callback<NumberFactResponse> {

        override fun onResponse(call: Call<NumberFactResponse>, response: Response<NumberFactResponse>) {
            val randomNumberFact = response.body()?.text
            Log.d("Network", "Call: $randomNumberFact")
            dispatch(DisplayRandomNumberFact(randomNumberFact))
        }

        override fun onFailure(call: Call<NumberFactResponse>, t: Throwable) {
            Log.d("Network", "Failed to load: " + t.localizedMessage)
        }
    })
}