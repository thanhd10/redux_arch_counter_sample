package com.example.reduxtestapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.reduxtestapp.actions.*
import com.example.reduxtestapp.middleware.networkMiddleware
import com.example.reduxtestapp.reducers.appReduce
import com.example.reduxtestapp.states.AppState
import kotlinx.android.synthetic.main.activity_main.*
import org.rekotlin.Store
import org.rekotlin.StoreSubscriber


class MainActivity : AppCompatActivity(), StoreSubscriber<AppState> {

    private val mainStore = Store(
        reducer = ::appReduce,
        state = null,
        middleware = listOf(networkMiddleware)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainStore.dispatch(LoadRandomNumberFact())
    }

    override fun onStart() {
        super.onStart()
        mainStore.subscribe(this)
        addListeners()
    }

    override fun onStop() {
        super.onStop()
        mainStore.unsubscribe(this)
        removeListeners()
    }

    private fun addListeners() {
        addButton.setOnClickListener { mainStore.dispatch(IncrementTapped()) }
        decrButton.setOnClickListener { mainStore.dispatch(DecrementTapped()) }
        doubleIncButton.setOnClickListener { mainStore.dispatch(DoubleIncTapped()) }
        doubleDecrButton.setOnClickListener { mainStore.dispatch(DoubleDecrTapped()) }
        refreshButton.setOnClickListener { mainStore.dispatch(LoadRandomNumberFact()) }
    }

    private fun removeListeners() {
        addButton.setOnClickListener(null)
        decrButton.setOnClickListener(null)
        doubleIncButton.setOnClickListener(null)
        doubleDecrButton.setOnClickListener(null)
        refreshButton.setOnClickListener(null)
    }

    override fun newState(state: AppState) {
        state.parentCounterState.apply {
            counterLabel.text = "${this.counterState.counter}"
            doubleCounterLabel.text = "${this.doubleCounterState.doubleCounter}"
        }
        state.randomNumberFactState.text?.apply {
            randomFactLabel.text = this
        }
    }
}
