package com.challenge.get.base.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.challenge.get.base.model.Direction
import com.challenge.get.base.model.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * [ViewModel] to manage directions, because feature module can not reference to a direction.
 */
@HiltViewModel
class NavigationViewModel @Inject constructor(): ViewModel() {

    private val mutableDirection: MutableLiveData<Event<Direction>> = MutableLiveData()
    val direction: LiveData<Event<Direction>> = mutableDirection

    fun lessonSuccess() {
        mutableDirection.value = Event(Direction.Success)
    }

}
