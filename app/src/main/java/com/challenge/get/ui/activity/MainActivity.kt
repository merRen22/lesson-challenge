package com.challenge.get.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.challenge.get.R
import com.challenge.get.base.model.Direction
import com.challenge.get.base.model.EventObserver
import androidx.navigation.findNavController
import com.challenge.get.base.viewmodel.NavigationViewModel
import com.challenge.get.lesson.LessonFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val navigationViewModel: NavigationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navigationViewModel.direction.observe(
            this,
            EventObserver { direction ->
                val navDirection = when (direction) {
                    is Direction.Success -> LessonFragmentDirections.actionLessonFragmentToSummaryFragment()
                }
                findNavController(R.id.host_fragment).navigate(navDirection)
            }
        )
    }

    override fun onBackPressed() {
        return
    }

}