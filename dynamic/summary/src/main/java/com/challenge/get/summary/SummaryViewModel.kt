package com.challenge.get.summary

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.challenge.get.base.ErrorHandler
import com.challenge.get.model.Summary
import com.challenge.get.repository.SummaryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * [ViewModel] to store and manage user-related data.
 * This should be Activity-Scope, because the data is used across screens.
 */
@HiltViewModel
class SummaryViewModel @Inject constructor(
    private val summaryRepository: SummaryRepository,
    private val errorHandler: ErrorHandler
) : ViewModel() {

    private val mutableSummaries: MutableLiveData<List<Summary>> = MutableLiveData()
    val summaries: LiveData<List<Summary>> = mutableSummaries

    init {
        findSummaries()
    }

    fun findSummaries() {
        viewModelScope.launch {
            runCatching {
                summaryRepository.findAllSummaries()
            }.onSuccess { summaries ->
                mutableSummaries.value = summaries
            }.onFailure { error ->
                errorHandler.handleError(error)
            }
        }
    }

    fun save(summary: Summary) {
        viewModelScope.launch {
            runCatching {
                summaryRepository.addSummary(summary)
                val summaries = mutableSummaries.value?.toMutableList() ?: mutableListOf()
                summaries.apply { add(summary) }
            }.onSuccess { summaries ->
                mutableSummaries.value = summaries
            }.onFailure { error ->
                errorHandler.handleError(error)
            }
        }
    }
}
