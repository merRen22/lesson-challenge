package com.challenge.get.lesson

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.challenge.get.base.ErrorHandler
import com.challenge.get.model.Lesson
import com.challenge.get.model.Summary
import com.challenge.get.repository.LessonRepository
import com.challenge.get.repository.SummaryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.threeten.bp.Instant
import javax.inject.Inject

/**
 * [ViewModel] to manage lesson data.
 */
@HiltViewModel
class LessonViewModel @Inject constructor(
    private val lessonRepository: LessonRepository,
    private val summaryRepository: SummaryRepository,
    private val errorHandler: ErrorHandler
) : ViewModel() {

    private val mutableStep = MutableLiveData<Lesson>()
    private lateinit var mutableSteps: MutableList<Lesson>
    private lateinit var mutableSummaries: MutableList<Summary>

    private var stepsJob: Job? = null

    val step: LiveData<Lesson> get() = mutableStep
    val steps: MutableList<Lesson> get() = mutableSteps
    val summaries: MutableList<Summary> get() = mutableSummaries

    private fun searchSteps() {
        val currentJob = stepsJob
        if (currentJob != null && currentJob.isActive) {
            currentJob.cancel()
        }
        stepsJob = viewModelScope.launch {
            runCatching {
                lessonRepository.getLessons()
            }.onSuccess { map ->
                mutableSteps = map as MutableList<Lesson>
                mutableStep.value = mutableSteps.first()
                mutableSummaries = mutableListOf(
                    Summary(
                        id = mutableSteps.first().id,
                        started = Instant.now().toString(),
                        completed = ""
                    )
                )
            }.onFailure { error ->
                if (error !is CancellationException) {
                    errorHandler.handleError(error)
                }
            }
        }
    }

    fun completeStep() {
        mutableSummaries.last().completed = Instant.now().toString()

        viewModelScope.launch {
            runCatching {
                summaryRepository.addSummary(mutableSummaries.last())
            }.onFailure {
                errorHandler.handleError(it)
            }
        }
    }

    fun nextStep() {
        mutableSteps.removeFirst()
        mutableStep.value = mutableSteps.first()
        mutableSummaries.add(
            Summary(
                id = mutableSteps.first().id,
                started = Instant.now().toString(),
                completed = ""
            )
        )
    }

    init {
        searchSteps()
    }


}