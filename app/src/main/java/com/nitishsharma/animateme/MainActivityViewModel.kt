package com.nitishsharma.animateme

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nitishsharma.animateme.utils.Event

class MainActivityViewModel : ViewModel() {
    val topImages = listOf(R.drawable.robot, R.drawable.trophy, R.drawable.gifts)
    private val _pageNumber: MutableLiveData<Event<Int>> = MutableLiveData(Event(0))
    val pageNumber: LiveData<Event<Int>> get() = _pageNumber

    fun updatePageNumber(number: Int) {
        if (number in 0..2) {
            _pageNumber.postValue(Event(number))
        }
    }
}