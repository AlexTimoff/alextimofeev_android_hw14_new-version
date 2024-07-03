package com.example.alextimofeev_android_hw14

sealed class State {
    data object Normal: State()
    data object Load: State()
    data object Error: State()

}