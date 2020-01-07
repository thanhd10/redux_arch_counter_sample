package com.example.reduxtestapp.reducers

import com.example.reduxtestapp.actions.*
import com.example.reduxtestapp.states.*
import org.rekotlin.Action

fun appReduce(action: Action, appState: AppState?): AppState =
    AppState(
        parentCounterState = parentCounterReduce(action, appState?.parentCounterState),
        randomNumberFactState = randomNumberFactReduce(action, appState?.randomNumberFactState)
    )

fun parentCounterReduce(
    action: Action,
    parentCounterState: ParentCounterState?
): ParentCounterState =
    ParentCounterState(
        counterState = counterReduce(action, parentCounterState?.counterState),
        doubleCounterState = doubleCounterReduce(action, parentCounterState?.doubleCounterState)
    )

fun counterReduce(action: Action, counterState: CounterState?): CounterState {
    var state = counterState ?: CounterState()
    when (action) {
        is IncrementTapped -> {
            state = state.copy(counter = state.counter + 1)
        }
        is DecrementTapped -> {
            state = state.copy(counter = state.counter - 1)
        }
    }
    return state
}

fun doubleCounterReduce(
    action: Action,
    doubleCounterState: DoubleCounterState?
): DoubleCounterState {
    var state = doubleCounterState ?: DoubleCounterState()
    when (action) {
        is DoubleIncTapped -> {
            state = state.copy(doubleCounter = state.doubleCounter + 2)
        }
        is DoubleDecrTapped -> {
            state = state.copy(doubleCounter = state.doubleCounter - 2)
        }
    }
    return state
}

fun randomNumberFactReduce(
    action: Action,
    randomNumberFactState: RandomNumberFactState?
): RandomNumberFactState {
    var state = randomNumberFactState ?: RandomNumberFactState()
    when (action) {
        is DisplayRandomNumberFact -> {
            state = state.copy(text = action.randomNumberFact)
        }
    }
    return state
}
