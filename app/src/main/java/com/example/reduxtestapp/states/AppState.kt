package com.example.reduxtestapp.states

import org.rekotlin.StateType

data class AppState(
    var parentCounterState: ParentCounterState,
    var randomNumberFactState: RandomNumberFactState
) : StateType

data class ParentCounterState(
    var counterState: CounterState,
    var doubleCounterState: DoubleCounterState
) : StateType

data class CounterState(
    var counter: Int = 0
) : StateType

data class DoubleCounterState(
    var doubleCounter: Int = 0
) : StateType

data class RandomNumberFactState(
    var text: String? = null
) : StateType