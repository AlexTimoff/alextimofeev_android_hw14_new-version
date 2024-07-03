package com.example.alextimofeev_android_hw14

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PersonProfileCardViewModel : ViewModel() {
    private var startProfileCard = true

    private val _state = MutableStateFlow<State>(State.Load)
    val state = _state.asStateFlow()

    private val _profile = MutableStateFlow<PersonProfile?>(null)
    val profile = _profile.asStateFlow()


    fun startProfileCard() {
        if (startProfileCard) {
            getDataFromRandomUser()
            startProfileCard = false
        }
    }

    fun getDataFromRandomUser() {
        viewModelScope.launch {
            _state.value = State.Load
            val response = RetrofitInstance.randomGenerateServiceApiResponse.getPersonProfile()
            if (response.isSuccessful) {
                _profile.value = response.body()
                _state.value = State.Normal

            } else {
                _state.value = State.Error
                Log.d("logAlex", "Something went wrong")
            }
        }
    }

}