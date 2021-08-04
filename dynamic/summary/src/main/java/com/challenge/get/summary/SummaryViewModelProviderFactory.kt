package com.challenge.get.summary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.challenge.get.base.ErrorHandler
import com.challenge.get.repository.SummaryRepository
import javax.inject.Inject

/**
 * Dagger-Hilt does not work with DFM.
 * This is an alternative way to archive injection.
 * https://github.com/google/dagger/issues/1865
 * https://developer.android.com/training/dependency-injection/hilt-multi-module#dfm
 */
class SummaryViewModelProviderFactory @Inject constructor(
    private val summaryRepository: SummaryRepository,
    private val errorHandler: ErrorHandler
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SummaryViewModel(summaryRepository, errorHandler) as T
    }
}